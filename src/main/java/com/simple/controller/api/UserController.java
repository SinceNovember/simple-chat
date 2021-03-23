package com.simple.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.simple.model.dto.UserDTO;
import com.simple.model.dto.UserWithFriendDTO;
import com.simple.model.entity.MsgHistory;
import com.simple.model.entity.User;
import com.simple.service.MsgHistoryService;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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

    @GetMapping
    public ResponseEntity<User> user(@SessionAttribute("user") User user){
        return ResponseEntity.ok(user);
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> info(@SessionAttribute("user") User user) {
        return  ResponseEntity.ok(userService.convertDetailTo(userService.getUserDetailInfo(user.getUserId())));
    }

    @PostMapping("/info")
    public ResponseEntity updateInfo(@SessionAttribute("user") User user, @NonNull @RequestBody String body) {
        try {
            JSONObject params = JSONObject.parseObject(body);
            user.setPhone(params.getString("phone"));
            user.setCity(params.getString("city"));
            user.setWebsite(params.getString("website"));
            user.setIntroduce(params.getString("introduce"));
            userService.update(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("信息保存失败");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

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
