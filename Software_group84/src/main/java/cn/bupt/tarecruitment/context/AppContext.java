package cn.bupt.tarecruitment.context;

import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.*;
import cn.bupt.tarecruitment.service.impl.*;
import cn.bupt.tarecruitment.util.AppPaths;

public final class AppContext {
    public static final CsvDataStore STORE = new CsvDataStore(AppPaths.dataRoot());
    public static final AuthService AUTH = new DefaultAuthService(STORE);
    public static final ProfileService PROFILES = new DefaultProfileService(STORE);
    public static final JobService JOBS = new DefaultJobService(STORE);
    public static final ApplicationService APPLICATIONS = new DefaultApplicationService(STORE, JOBS);
    public static final MatchService MATCHING = new DefaultMatchService();
    public static final WorkloadService WORKLOAD = new DefaultWorkloadService(STORE);

    private AppContext() {}
}
