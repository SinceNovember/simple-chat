package com.simple.config;

import com.simple.factory.StringToEnumConverterFactory;
import com.simple.interceptor.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    /**
     * 注册将字符转枚举的转换测
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
