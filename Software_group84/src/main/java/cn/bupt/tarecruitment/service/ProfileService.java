package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.Profile;
import java.util.List;

public interface ProfileService {
    Profile findByUserId(String userId);
    List<Profile> listAll();
    void save(Profile profile);
}
