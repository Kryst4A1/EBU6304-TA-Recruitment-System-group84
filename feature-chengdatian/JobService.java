package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.JobPost;
import java.util.List;

public interface JobService {
    List<JobPost> listOpenJobs();
    List<JobPost> listByOwner(String ownerId);
    List<JobPost> listAll();
    JobPost findById(String id);
    JobPost save(JobPost job);
    void close(String jobId, String ownerId);
    void reopen(String jobId, String ownerId);
}
