package com.simple.dao;


import com.simple.core.MyMapper;
import com.simple.model.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends MyMapper<User> {

    @Select("select * from users  where loginid =#{loginId} and password =#{password}")
    User login(User user);
}
