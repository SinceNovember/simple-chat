package com.simple.service;

import com.simple.core.Service;
import com.simple.model.entity.User;

public interface UserService extends Service<User> {

    User login(User user);
}
