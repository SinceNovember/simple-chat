package com.simple.server;

import com.simple.server.handler.TextToMessageHandler;
import com.simple.server.handler.WebSocketMessageHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class WebSocketChatServerInitializer extends ChannelInitializer<Channel> {

    @Resource
    private TextToMessageHandler textToMessageHandler;

    @Resource
    private WebSocketMessageHandler webSocketMessageHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new ChunkedWriteHandler());
//        pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(textToMessageHandler);
        pipeline.addLast(webSocketMessageHandler);
    }
}
