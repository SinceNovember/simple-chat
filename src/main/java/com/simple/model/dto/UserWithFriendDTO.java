package com.simple.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simple.model.dto.base.OutputConverter;
import com.simple.model.entity.UserWithFriend;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 博客DTO,继承了DTO从entity那获取数据
 */
@Data
public class UserWithFriendDTO implements OutputConverter<UserWithFriendDTO, UserWithFriend> {

    private String userId;

    private String displayName;

    private int noReadCount;

    private boolean hasNoReadMsg;

    private String lastMsg;

    @JsonFormat(pattern="MM-dd hh:mm:ss")
    private Date lastMsgTime;

    private List<UserWithFriendDTO> friends;


}
