package com.simple.config;

import com.simple.factory.StringToEnumConverterFactory;
import com.simple.interceptor.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {

    //拦截的路径WebMvcConfigurer
    @Value("#{'${interceptor.path}'.split(',')}")
    private List<String> interceptorPath;

    //放行的路径
    @Value("#{'${interceptor.excludePath}'.split(',')}")
    private List<String> interceptorExcludePath;

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    @Resource
    private RequestMappingHandlerAdapter adapter;

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

    /**
     * 设置SpringMvc的response包装实体
     * @throws Exception
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        // 获取SpringMvc的ReturnValueHandlers
//        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
//        // 新建一个List来保存替换后的Handler的List
//        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
//        // 循环遍历找出RequestResponseBodyMethodProcessor
//        for (HandlerMethodReturnValueHandler handler : handlers) {
//            if (handler instanceof RequestResponseBodyMethodProcessor) {
//                // 创建定制的Json格式处理Handler
//                ResultWrapReturnValueHandler decorator = new ResultWrapReturnValueHandler(handler);
//                // 使用定制的Json格式处理Handler替换原有的RequestResponseBodyMethodProcessor
//                int index = handlers.indexOf(handler);
//                handlers.set(index, decorator);
//                break;
//            }
//        }
//        // 重新设置SpringMVC的ReturnValueHandlers
//        adapter.setReturnValueHandlers(handlers);
//    }
}
