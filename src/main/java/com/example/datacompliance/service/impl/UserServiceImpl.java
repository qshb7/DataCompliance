package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.User;
import com.example.datacompliance.mapper.UserMapper;
import com.example.datacompliance.service.UserService;
import com.example.datacompliance.utils.Md5Util;
import com.example.datacompliance.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author zhengzh1
 * @date 2024/3/2 13:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String userName) {
        User u = userMapper.findByName(userName);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}

