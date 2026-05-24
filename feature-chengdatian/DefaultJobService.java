package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.JobPost;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.JobService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultJobService implements JobService {
    private final CsvDataStore store;

    public DefaultJobService(CsvDataStore store) {
        this.store = store;
    }

    @Override
    public List<JobPost> listOpenJobs() {
        return store.readJobs().stream()
                .filter(j -> "OPEN".equalsIgnoreCase(j.getStatus()))
                .sorted(Comparator.comparing(JobPost::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<JobPost> listByOwner(String ownerId) {
        return store.readJobs().stream()
                .filter(j -> j.getOwnerId().equals(ownerId))
                .sorted(Comparator.comparing(JobPost::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<JobPost> listAll() {
        return store.readJobs();
    }

    @Override
    public JobPost findById(String id) {
        if (id == null) return null;
        return store.readJobs().stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public JobPost save(JobPost job) {
        List<JobPost> jobs = store.readJobs();
        if (job.getId() == null || job.getId().trim().isEmpty()) {
            job.setId("j-" + System.currentTimeMillis());
            job.setCreatedAt(LocalDateTime.now().toString());
            job.setStatus("OPEN");
            jobs.add(job);
        } else {
            boolean updated = false;
            for (int i = 0; i < jobs.size(); i++) {
                if (jobs.get(i).getId().equals(job.getId())) {
                    JobPost old = jobs.get(i);
                    job.setCreatedAt(old.getCreatedAt());
                    if (job.getStatus() == null || job.getStatus().trim().isEmpty()) job.setStatus(old.getStatus());
                    jobs.set(i, job);
                    updated = true;
                    break;
                }
            }
            if (!updated) jobs.add(job);
        }
        store.writeJobs(jobs);
        return job;
    }

    @Override
    public void close(String jobId, String ownerId) {
        setStatus(jobId, ownerId, "CLOSED");
    }

    @Override
    public void reopen(String jobId, String ownerId) {
        setStatus(jobId, ownerId, "OPEN");
    }

    private void setStatus(String jobId, String ownerId, String status) {
        List<JobPost> jobs = store.readJobs();
        for (JobPost job : jobs) {
            if (job.getId().equals(jobId) && job.getOwnerId().equals(ownerId)) {
                job.setStatus(status);
            }
        }
        store.writeJobs(jobs);
    }
}
