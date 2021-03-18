package com.simple.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;

@Data
@ToString
public class User {
    @Id
    private String userId;

    private String loginId;

    private String password;

    private String displayName;
}
