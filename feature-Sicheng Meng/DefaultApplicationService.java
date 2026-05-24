package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.Application;
import cn.bupt.tarecruitment.model.Assignment;
import cn.bupt.tarecruitment.model.JobPost;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.ApplicationService;
import cn.bupt.tarecruitment.service.JobService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultApplicationService implements ApplicationService {
    private final CsvDataStore store;
    private final JobService jobs;

    public DefaultApplicationService(CsvDataStore store, JobService jobs) {
        this.store = store;
        this.jobs = jobs;
    }

    @Override
    public Application apply(String jobId, String taUserId, String statement) {
        JobPost job = jobs.findById(jobId);
        if (job == null) throw new IllegalArgumentException("Job not found.");
        if (!"OPEN".equalsIgnoreCase(job.getStatus())) throw new IllegalArgumentException("This job is closed.");
        if (hasActiveApplication(jobId, taUserId)) throw new IllegalArgumentException("You have already applied for this job.");
        List<Application> applications = store.readApplications();
        Application app = new Application("app-" + System.currentTimeMillis(), jobId, taUserId, "SUBMITTED",
                statement == null ? "" : statement.trim(), LocalDateTime.now().toString());
        applications.add(app);
        store.writeApplications(applications);
        return app;
    }

    @Override
    public List<Application> listByTa(String taUserId) {
        return store.readApplications().stream()
                .filter(a -> a.getTaUserId().equals(taUserId))
                .sorted(Comparator.comparing(Application::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Application> listByJob(String jobId) {
        return store.readApplications().stream()
                .filter(a -> a.getJobId().equals(jobId))
                .collect(Collectors.toList());
    }

    @Override
    public Application findById(String id) {
        if (id == null) return null;
        return store.readApplications().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateStatus(String applicationId, String status) {
        List<Application> applications = store.readApplications();
        Application target = null;
        for (Application app : applications) {
            if (app.getId().equals(applicationId)) {
                app.setStatus(status);
                target = app;
                break;
            }
        }
        store.writeApplications(applications);
        if (target != null) syncAssignment(target);
    }

    @Override
    public boolean hasActiveApplication(String jobId, String taUserId) {
        return store.readApplications().stream()
                .anyMatch(a -> a.getJobId().equals(jobId)
                        && a.getTaUserId().equals(taUserId)
                        && !"REJECTED".equalsIgnoreCase(a.getStatus()));
    }

    private void syncAssignment(Application app) {
        List<Assignment> assignments = store.readAssignments();
        boolean exists = assignments.stream().anyMatch(a -> a.getJobId().equals(app.getJobId()) && a.getTaUserId().equals(app.getTaUserId()));
        if ("SELECTED".equalsIgnoreCase(app.getStatus())) {
            if (!exists) {
                JobPost job = jobs.findById(app.getJobId());
                int hours = job == null ? 0 : job.getHoursPerWeek();
                assignments.add(new Assignment("as-" + System.currentTimeMillis(), app.getJobId(), app.getTaUserId(), hours));
            }
        } else {
            assignments.removeIf(a -> a.getJobId().equals(app.getJobId()) && a.getTaUserId().equals(app.getTaUserId()));
        }
        store.writeAssignments(assignments);
    }
}
