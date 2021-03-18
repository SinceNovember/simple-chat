package com.simple.service.impl;

import com.simple.core.AbstractService;
import com.simple.dao.UserMapper;
import com.simple.model.entity.User;
import com.simple.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User getUserInfo(String userId) {
        return userMapper.getUserInfo(userId);
    }

}
