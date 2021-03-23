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

    private String  city;

    private String phone;

    private String website;

    private String introduce;

    private Integer allowView;

//    private int noReadCount;
//
//    private boolean hasNoReadMsg;
//
//    private List<User> friends;
}
