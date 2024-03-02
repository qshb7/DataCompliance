package com.example.datacompliance.service;

import com.example.datacompliance.entity.User;

/**
 * @author zhengzh1
 * @date 2024/3/2 13:41
 */
public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    void register(String username, String password);

    // 更新
    void update(User user);

    // 更新头像
    void updateAvatar(String avatarUrl);

    // 更新密码
    void updatePwd(String newPwd);
}
