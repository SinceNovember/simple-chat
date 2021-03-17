package com.simple.server.handler.message;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 私聊消息处理
 */
public class singleMsgHandler extends CommonMsgHandler {
    @Override
    public void handleMessage(JSONObject param, ChannelHandlerContext ctx) {
        System.out.println(param.getString("content"));
        ctx.channel().writeAndFlush(param.getString("content"));
    }
}
