package com.simple.server.handler.message;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 私聊消息处理
 */
public class singleMsgHandler extends CommonMsgHandler {
    @Override
    public void handleMessage(ChannelHandlerContext ctx, Msg msg) {
        ctx.channel().writeAndFlush(msg.getContent());
    }
}
