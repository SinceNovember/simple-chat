package com.simple.model.dto;

import com.simple.model.dto.base.OutputConverter;
import com.simple.model.entity.User;

public class UserInfoDTO implements OutputConverter<UserInfoDTO, User> {

    private String userId;

    private String displayName;

    private String city;

    private String phone;

    private String website;

    private String introduce;

    private String allowView;
}
