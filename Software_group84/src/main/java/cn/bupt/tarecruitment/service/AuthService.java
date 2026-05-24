package cn.bupt.tarecruitment.service;

import cn.bupt.tarecruitment.model.Role;
import cn.bupt.tarecruitment.model.User;

public interface AuthService {
    User login(String username, String password);
    User registerTa(String username, String password, String displayName);
    User registerUser(String username, String password, String displayName, Role role);
}
