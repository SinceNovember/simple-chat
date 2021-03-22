package com.simple.controller.api;


import com.simple.model.dto.UserWithFriendDTO;
import com.simple.model.entity.MsgHistory;
import com.simple.model.entity.User;
import com.simple.service.MsgHistoryService;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private MsgHistoryService msgHistoryService;

    @GetMapping("/info")
    public ResponseEntity<User> info(@SessionAttribute("user") User user){
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    public User test(){
        return new User();
    }
    @GetMapping("/recent")
    public ResponseEntity<UserWithFriendDTO> recent(@SessionAttribute("user") User user){
        UserWithFriendDTO userInfo  =  userService.convertTo(userService.getUserInfo(user.getUserId()));
        //获取未读到得消息个数
        userInfo.getFriends().forEach(friend ->{
            int noReadCount = msgHistoryService.countNoReadHistory(friend.getUserId(), user.getUserId());
            if (noReadCount > 0) {
                friend.setNoReadCount(noReadCount);
                friend.setHasNoReadMsg(true);
            }
            MsgHistory lastMsgHistory = msgHistoryService.getLastHistory(friend.getUserId(), user.getUserId());
            if (lastMsgHistory != null) {
                friend.setLastMsg(lastMsgHistory.getContent());
                friend.setLastMsgTime(lastMsgHistory.getCreateTime());
            }
        });
        return ResponseEntity.ok(userInfo);

    }
}
