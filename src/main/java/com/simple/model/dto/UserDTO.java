package com.simple.model.dto;

import com.simple.model.dto.base.OutputConverter;
import com.simple.model.entity.User;

public class UserDTO implements OutputConverter<UserDTO, User> {

    private String userId;

    private String displayName;

}
