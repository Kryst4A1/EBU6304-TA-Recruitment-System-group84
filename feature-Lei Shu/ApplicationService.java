package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.Application;
import java.util.List;

public interface ApplicationService {
    Application apply(String jobId, String taUserId, String statement);
    List<Application> listByTa(String taUserId);
    List<Application> listByJob(String jobId);
    Application findById(String id);
    void updateStatus(String applicationId, String status);
    boolean hasActiveApplication(String jobId, String taUserId);
}
