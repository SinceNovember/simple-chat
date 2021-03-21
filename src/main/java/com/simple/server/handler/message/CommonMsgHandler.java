package com.simple.server.handler.message;

import com.simple.server.handler.WebSocketMessageHandler;
import com.simple.service.MsgHistoryService;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public abstract class CommonMsgHandler implements MsgHandler {

    @Resource
    protected MsgHistoryService msgHistoryService;

    protected ChannelGroup getChannels(){
        return WebSocketMessageHandler.channels;
    }
}
