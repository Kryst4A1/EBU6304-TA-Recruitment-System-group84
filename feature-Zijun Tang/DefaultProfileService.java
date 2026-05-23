package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.Profile;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.ProfileService;

import java.util.List;

public class DefaultProfileService implements ProfileService {
    private final CsvDataStore store;

    public DefaultProfileService(CsvDataStore store) {
        this.store = store;
    }

    @Override
    public Profile findByUserId(String userId) {
        return store.readProfiles().stream()
                .filter(p -> p.getUserId().equals(userId))
                .findFirst()
                .orElse(new Profile(userId, "", "", "", "", "", "", "", "", ""));
    }

    @Override
    public List<Profile> listAll() {
        return store.readProfiles();
    }

    @Override
    public void save(Profile profile) {
        List<Profile> profiles = store.readProfiles();
        boolean updated = false;
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUserId().equals(profile.getUserId())) {
                profiles.set(i, profile);
                updated = true;
                break;
            }
        }
        if (!updated) profiles.add(profile);
        store.writeProfiles(profiles);
    }
}
