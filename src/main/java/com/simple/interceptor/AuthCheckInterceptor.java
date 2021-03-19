package com.simple.interceptor;

import com.simple.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
@Slf4j
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
            throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect( "/");
            return false;
        }
        return true;
    }
}
