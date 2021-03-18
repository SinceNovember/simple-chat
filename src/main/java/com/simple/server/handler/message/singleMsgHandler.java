package com.simple.server.handler.message;

import com.simple.utils.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 私聊消息处理
 */
@Slf4j
@Component
public class singleMsgHandler extends CommonMsgHandler {
    @Override
    public void handleMessage(ChannelHandlerContext ctx, Msg msg) {

        //获取接收方与服务器端连接的channel，并向接收方发送数据
        Channel channel = ChannelUtil.getChannel(msg.getToId());
        if(channel == null){
           log.info(msg.getToId()+"不在线");
        }else {
//            getChannels().writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.getContent()));
            channel.writeAndFlush(new TextWebSocketFrame(msg.getContent()));
        }
    }
}
