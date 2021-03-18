package com.simple.server.handler.message;

import com.simple.utils.AttributeKeys;
import com.simple.utils.ChannelUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理登录的消息请求，主要是为了加入到ChannelGroup中
 */
public class loginMsgHandler extends CommonMsgHandler {
    @Override
    public void handleMessage(ChannelHandlerContext ctx, Msg msg) {
        ctx.channel().attr(AttributeKeys.LOGIN).set(true);

        // 为channel添加userId属性，方便在删除channel时能快速从Map中查找并删除
        ctx.channel().attr(AttributeKeys.USER_ID).set(msg.getFromId());
        //保存到ChannelGroup中
        ChannelUtil.saveChannel(msg.getFromId(), ctx.channel());
    }
}
