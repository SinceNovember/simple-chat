package com.simple.model.vo;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultVO{
    /** 响应码 */
    private Integer code;
    /** 响应信息 */
    private String message;
    /** 数据 */
    private Object data;
    /** 请求地址 */
    private String url;

    public ResultVO(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static ResultVO success(Object data) {
        return new ResultVO(HttpResponseStatus.OK.code(), data);
    }

}
