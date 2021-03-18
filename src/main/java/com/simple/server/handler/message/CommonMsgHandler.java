package com.simple.server.handler.message;

import com.simple.server.handler.WebSocketMessageHandler;
import io.netty.channel.group.ChannelGroup;

public abstract class CommonMsgHandler implements MsgHandler {
    protected ChannelGroup getChannels(){
        return WebSocketMessageHandler.channels;
    }
}
