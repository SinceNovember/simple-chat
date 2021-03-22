package com.simple.controller;

import com.simple.model.entity.User;
import com.simple.model.params.UserParam;
import com.simple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @PostMapping("/login")
    public String login(UserParam userParam, HttpSession session){
        try {
            User user = userService.login(userParam.convertTo());
            if (user != null) {
                session.setAttribute("user", user);
                return "index";
            }
        } catch (Exception e) {
            log.error("{}登陆失败", "用户");
        }
        return "login";
    }
}
