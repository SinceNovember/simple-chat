package com.simple.server.handler.message;

import com.simple.model.entity.MsgHistory;
import com.simple.model.enums.MsgReadType;
import com.simple.model.enums.MsgType;
import com.simple.utils.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 私聊消息处理
 */
@Slf4j
@Component("SINGlE_MSG")
public class SingleMsgHandler extends CommonMsgHandler {

    public void handleMessage(ChannelHandlerContext ctx, Msg msg) {

        //获取接收方与服务器端连接的channel，并向接收方发送数据
        Channel channel = ChannelUtil.getChannel(msg.getToId());
        if(channel == null){
           log.info(msg.getToId()+"不在线");
           saveMsg(msg);
        } else {
            channel.writeAndFlush(new TextWebSocketFrame(msg.getContent())).addListener(future -> {
                saveMsg(msg);
            });
        }
    }

    /**
     * 保存单聊信息
     * @param msg
     */
    private void saveMsg(Msg msg) {
        MsgHistory msgHistory = new MsgHistory();
        msgHistory.setRowguid(UUID.randomUUID().toString());
        msgHistory.setFromId(msg.getFromId());
        msgHistory.setToId(msg.getToId());
        msgHistory.setContent(msg.getContent());
        msgHistory.setType(MsgType.TEXT);
        msgHistory.setCreateTime(new Date());
        msgHistory.setIsRead(MsgReadType.NOT_READ);
        msgHistoryService.save(msgHistory);
    }
}
