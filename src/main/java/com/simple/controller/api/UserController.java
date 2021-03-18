package com.simple.controller.api;

import com.simple.model.entity.User;
import com.simple.model.params.UserParam;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/user")
@SessionAttributes("user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    @ModelAttribute("user")
    public String login(@RequestBody UserParam userParam){
        try {
            User user = userService.login(userParam.convertTo());
            if (user != null) {
                System.out.println("1");
            }
        } catch (Exception e) {
            log.error("{}登陆失败", "用户");
        }
        return "forward:login.html";
    }

    @PostMapping("/test")
    public void test(@ModelAttribute("user") User user) {
        System.out.println(user);
    }
}
