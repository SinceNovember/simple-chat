package com.simple.handler;

import com.simple.model.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 包装返回结果处理器
 */
public class ResultWrapReturnValueHandler implements HandlerMethodReturnValueHandler {

    //委托的处理器
    private final HandlerMethodReturnValueHandler delegate;

    public ResultWrapReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    /**
     * 判断返回类型是否需要转成字符串返回
     * @param returnType 方法返回类型
     * @return 需要转换返回true，否则返回false
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        System.out.println(delegate.supportsReturnType(returnType));
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        // 委托SpringMVC默认的RequestResponseBodyMethodProcessor进行序列化
        if (returnValue instanceof ResultVO) {
            System.out.println(returnValue);
            System.out.println(ResultVO.success(returnValue));
        }
        delegate.handleReturnValue(returnValue instanceof ResultVO ? returnValue : ResultVO.success(returnValue), methodParameter, modelAndViewContainer, nativeWebRequest);
    }
}
