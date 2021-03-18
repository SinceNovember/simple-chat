package com.simple.controller.api;


import com.simple.model.entity.User;
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

    @GetMapping("/info")
    public ResponseEntity<User> info(@SessionAttribute("user") User user){
        User userInfo = userService.getUserInfo(user.getUserId());
        return ResponseEntity.ok(userInfo);
    }
}
