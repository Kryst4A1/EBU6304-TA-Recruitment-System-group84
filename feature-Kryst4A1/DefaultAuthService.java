package cn.bupt.tarecruitment.service.impl;

import cn.bupt.tarecruitment.model.Profile;
import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;
import cn.bupt.tarecruitment.repository.CsvDataStore;
import cn.bupt.tarecruitment.service.AuthService;

import java.util.List;
import java.util.Locale;

public class DefaultAuthService implements AuthService {
    private final CsvDataStore store;

    public DefaultAuthService(CsvDataStore store) {
        this.store = store;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null) return null;
        String name = username.trim();
        for (User user : store.readUsers()) {
            if (user.getUsername().equalsIgnoreCase(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User registerTa(String username, String password, String displayName) {
        return registerUser(username, password, displayName, Role.TA);
    }

    @Override
    public User registerUser(String username, String password, String displayName, Role role) {
        if (blank(username) || blank(password)) throw new IllegalArgumentException("Username and password are required.");
        if (role == null) throw new IllegalArgumentException("Role is required.");
        String cleanName = username.trim();
        List<User> users = store.readUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(cleanName)) {
                throw new IllegalArgumentException("Username already exists.");
            }
        }
        String prefix = role == Role.ADMIN ? "admin" : role.name().toLowerCase(Locale.ROOT);
        String id = "u-" + prefix + "-" + System.currentTimeMillis();
        String display = blank(displayName) ? cleanName : displayName.trim();
        User user = new User(id, cleanName.toLowerCase(Locale.ROOT), password, role, display);
        users.add(user);
        store.writeUsers(users);

        if (role == Role.TA) {
            List<Profile> profiles = store.readProfiles();
            profiles.add(new Profile(id, display, "", "", "", "", "", "", "", ""));
            store.writeProfiles(profiles);
        }
        return user;
    }

    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
}
