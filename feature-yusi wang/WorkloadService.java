package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.WorkloadRecommendation;
import cn.bupt.tarecruitment.model.WorkloadRow;
import java.util.List;

public interface WorkloadService {
    int DEFAULT_THRESHOLD_HOURS = 12;
    List<WorkloadRow> buildRows();
    List<WorkloadRecommendation> recommendRebalancing();
}
