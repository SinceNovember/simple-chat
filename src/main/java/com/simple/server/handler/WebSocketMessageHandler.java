package com.simple.server.handler;


import com.alibaba.fastjson.JSONObject;
import com.simple.server.handler.message.Msg;
import com.simple.server.handler.message.MsgHandler;
import com.simple.server.handler.message.MsgHandlerFactory;
import com.simple.utils.AttributeKeys;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理websocket请求
 */
@Slf4j
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<Msg> {
    //存放所有已经连接的Channel
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
//        handlerWebSocketFrame(ctx, frame);
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        handlerMsg(ctx, msg);
    }

    public void handlerMsg(ChannelHandlerContext ctx, Msg msg) {
        MsgHandler handler = MsgHandlerFactory.getHandler(msg.getType());
        handler.handleMessage(ctx, msg);
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        // 关闭请求
//        if (frame instanceof CloseWebSocketFrame) {
//            WebSocketServerHandshaker handshaker =
//                    Constant.webSocketHandshakerMap.get(ctx.channel().id().asLongText());
//            if (handshaker == null) {
//                sendErrorMessage(ctx, "不存在的客户端连接！");
//            } else {
//                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
//            }
//            return;
//        }
//        // ping请求
//        if (frame instanceof PingWebSocketFrame) {
//            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
//            return;
//        }
//        // 只支持文本格式，不支持二进制消息
//        if (!(frame instanceof TextWebSocketFrame)) {
//            sendErrorMessage(ctx, "仅支持文本(Text)格式，不支持二进制消息");
//        }

        // 客服端发送过来的消息
        String request = ((TextWebSocketFrame)frame).text();
        JSONObject param = null;
        try {
            param = JSONObject.parseObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        MsgHandler msg = MsgHandlerFactory.getHandler(param.getString("type"));
//        msg.handleMessage(param, ctx);
        log.info("服务端收到新信息：" + request);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("用户{}断线",ctx.channel().attr(AttributeKeys.USER_ID).get());
//        ChannelUtil.removeChannel(ctx.channel().attr(AttributeKeys.USER_ID).get());
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
        cause.printStackTrace();
        ctx.close();
    }

}
