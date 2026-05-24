package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.*;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.WorkloadService;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultWorkloadService implements WorkloadService {
    private final CsvDataStore store;

    public DefaultWorkloadService(CsvDataStore store) {
        this.store = store;
    }

    @Override
    public List<WorkloadRow> buildRows() {
        List<User> tas = store.readUsers().stream().filter(u -> u.getRole() == Role.TA).collect(Collectors.toList());
        Map<String, JobPost> jobMap = store.readJobs().stream().collect(Collectors.toMap(JobPost::getId, j -> j, (a, b) -> a));
        Map<String, Integer> totals = new HashMap<>();
        Map<String, List<String>> labels = new HashMap<>();
        for (Assignment asg : store.readAssignments()) {
            totals.merge(asg.getTaUserId(), asg.getHoursPerWeek(), Integer::sum);
            JobPost job = jobMap.get(asg.getJobId());
            String label = (job == null ? asg.getJobId() : job.label()) + " (" + asg.getHoursPerWeek() + "h)";
            labels.computeIfAbsent(asg.getTaUserId(), k -> new ArrayList<>()).add(label);
        }
        List<WorkloadRow> rows = new ArrayList<>();
        for (User ta : tas) {
            int total = totals.getOrDefault(ta.getId(), 0);
            rows.add(new WorkloadRow(ta.getId(), ta.getDisplayName(), total, labels.getOrDefault(ta.getId(), List.of()), total > DEFAULT_THRESHOLD_HOURS));
        }
        rows.sort(Comparator.comparingInt(WorkloadRow::getTotalHours).reversed());
        return rows;
    }

    @Override
    public List<WorkloadRecommendation> recommendRebalancing() {
        List<User> tas = store.readUsers().stream().filter(u -> u.getRole() == Role.TA).collect(Collectors.toList());
        Map<String, User> userMap = tas.stream().collect(Collectors.toMap(User::getId, u -> u));
        Map<String, JobPost> jobMap = store.readJobs().stream().collect(Collectors.toMap(JobPost::getId, j -> j, (a, b) -> a));
        List<Assignment> assignments = store.readAssignments();

        Map<String, Integer> totals = new HashMap<>();
        for (User ta : tas) totals.put(ta.getId(), 0);
        for (Assignment a : assignments) totals.merge(a.getTaUserId(), a.getHoursPerWeek(), Integer::sum);

        List<WorkloadRecommendation> out = new ArrayList<>();
        List<User> overloaded = tas.stream()
                .filter(u -> totals.getOrDefault(u.getId(), 0) > DEFAULT_THRESHOLD_HOURS)
                .sorted((a, b) -> Integer.compare(totals.getOrDefault(b.getId(), 0), totals.getOrDefault(a.getId(), 0)))
                .collect(Collectors.toList());

        for (User from : overloaded) {
            int fromTotal = totals.getOrDefault(from.getId(), 0);
            if (fromTotal <= DEFAULT_THRESHOLD_HOURS) continue;
            Assignment candidate = assignments.stream()
                    .filter(a -> a.getTaUserId().equals(from.getId()))
                    .max(Comparator.comparingInt(Assignment::getHoursPerWeek))
                    .orElse(null);
            if (candidate == null) continue;
            User receiver = tas.stream()
                    .filter(u -> !u.getId().equals(from.getId()))
                    .filter(u -> totals.getOrDefault(u.getId(), 0) + candidate.getHoursPerWeek() <= DEFAULT_THRESHOLD_HOURS)
                    .min(Comparator.comparingInt(u -> totals.getOrDefault(u.getId(), 0)))
                    .orElse(null);
            if (receiver == null) continue;
            totals.put(from.getId(), fromTotal - candidate.getHoursPerWeek());
            totals.put(receiver.getId(), totals.getOrDefault(receiver.getId(), 0) + candidate.getHoursPerWeek());
            JobPost job = jobMap.get(candidate.getJobId());
            String label = job == null ? candidate.getJobId() : job.label();
            String reason = "Move " + candidate.getHoursPerWeek() + "h from " + from.getDisplayName()
                    + " to " + receiver.getDisplayName() + " to reduce overload while keeping the receiver within "
                    + DEFAULT_THRESHOLD_HOURS + "h.";
            out.add(new WorkloadRecommendation(from.getDisplayName(), receiver.getDisplayName(), label, candidate.getHoursPerWeek(), reason));
        }
        return out;
    }
}
