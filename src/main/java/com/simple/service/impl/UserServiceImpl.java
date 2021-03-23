package com.simple.service.impl;

import com.simple.core.AbstractService;
import com.simple.dao.UserMapper;
import com.simple.model.dto.UserDTO;
import com.simple.model.dto.UserWithFriendDTO;
import com.simple.model.entity.User;
import com.simple.model.entity.UserWithFriend;
import com.simple.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public UserWithFriend getUserInfo(String userId) {
        return userMapper.getUserInfo(userId);
    }

    /**
     * 获取用户的详细信息
     * @param userId
     * @return
     */
    @Override
    public User getUserDetailInfo(String userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public UserDTO convertDetailTo(User user) {
        Assert.notNull(user,"user is not null");
        return new UserDTO().covertFrom(user);
    }

    @Override
    public List<UserDTO> convertDetailTo(List<User> users) {
        return users.stream()
                .map(this::convertDetailTo)
                .collect(Collectors.toList());
    }

    @Override
    public UserWithFriendDTO convertTo(UserWithFriend userWithFriend) {
        Assert.notNull(userWithFriend,"userWithFriend is not null");
        UserWithFriendDTO userWithFriendDTO = new UserWithFriendDTO().covertFrom(userWithFriend);
        for (int i = 0; i < userWithFriendDTO.getFriends().size(); i++) {
            userWithFriendDTO.getFriends().set(i, new UserWithFriendDTO().covertFrom(userWithFriend.getFriends().get(i)));
        }

        return userWithFriendDTO;
    }

    @Override
    public List<UserWithFriendDTO> convertTo(List<UserWithFriend> userWithFriend) {
        return userWithFriend.stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }


}
