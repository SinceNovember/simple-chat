package com.simple.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserWithFriend extends User{

    private List<UserWithFriend> friends;


}
