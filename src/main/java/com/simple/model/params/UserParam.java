package com.simple.model.params;

import com.simple.model.dto.base.InputConverter;
import com.simple.model.entity.User;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserParam implements InputConverter<User> {
    private String loginId;

    private String password;
}
