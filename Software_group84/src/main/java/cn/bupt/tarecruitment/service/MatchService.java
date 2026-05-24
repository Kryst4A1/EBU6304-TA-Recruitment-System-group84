package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.JobPost;
import cn.bupt.tarecruitment.model.Profile;
import cn.bupt.tarecruitment.model.SkillMatch;

public interface MatchService {
    SkillMatch match(Profile profile, JobPost job);
}
