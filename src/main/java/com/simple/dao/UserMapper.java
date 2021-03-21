package com.simple.dao;


import com.simple.core.MyMapper;
import com.simple.model.entity.User;
import com.simple.model.entity.UserWithFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User> {

    @Select("select * from user  where loginid =#{loginId} and password =#{password}")
    @ResultMap("BaseResultMap")
    User login(User user);

    @Select("select * from user  where userId =#{userId}")
    @ResultMap("BaseWithFriendResultMap")
    UserWithFriend getUserInfo(String userId);

    @Select("select b.* from user_friend_relation a join user b on a.friendId = b.userId where a.userId = #{userId}")
    List<UserWithFriend>  listUserFriend(String userId);
}
