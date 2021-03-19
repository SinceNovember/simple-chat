package com.simple.config;

import com.simple.interceptor.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //拦截的路径
    @Value("#{'${interceptor.path}'.split(',')}")
    private List<String> interceptorPath;

    //放行的路径
    @Value("#{'${interceptor.excludePath}'.split(',')}")
    private List<String> interceptorExcludePath;

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    //设置默认打开页面
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("redirect:login");
//    }

    /**
     * 添加拦截器配置
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCheckInterceptor).addPathPatterns(interceptorPath).excludePathPatterns(interceptorExcludePath);
    }
}
