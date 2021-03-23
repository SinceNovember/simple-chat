package com.simple.service;

import com.simple.core.Service;
import com.simple.model.dto.UserDTO;
import com.simple.model.dto.UserWithFriendDTO;
import com.simple.model.entity.User;
import com.simple.model.entity.UserWithFriend;
import org.springframework.lang.NonNull;

import java.util.List;
//import com.simple.model.entity.UserWithFriend;

public interface UserService extends Service<User> {

    User login(User user);

    UserWithFriend getUserInfo(String userId);

    User getUserDetailInfo(String userId);

    @NonNull
    UserWithFriendDTO convertTo(@NonNull UserWithFriend userWithFriend) ;

    @NonNull
    List<UserWithFriendDTO> convertTo(@NonNull List<UserWithFriend> userWithFriend) ;

    @NonNull
    UserDTO convertDetailTo(User user);

    @NonNull
    List<UserDTO> convertDetailTo(List<User> users);


}
