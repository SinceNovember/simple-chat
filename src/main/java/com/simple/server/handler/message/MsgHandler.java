package com.simple.server.handler.message;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理接口
 */
public interface MsgHandler {

    public void handleMessage(ChannelHandlerContext ctx, Msg msg);
}
