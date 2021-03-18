package com.simple.controller.api;

import com.simple.model.entity.User;
import com.simple.model.params.UserParam;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody UserParam userParam, HttpSession session){
        try {
            User user = userService.login(userParam.convertTo());
            if (user != null) {
                session.setAttribute("user", user);
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            log.error("{}登陆失败", "用户");
        }
        return null;
    }

}
