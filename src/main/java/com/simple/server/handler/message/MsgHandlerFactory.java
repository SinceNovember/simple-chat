package com.simple.server.handler.message;

import java.util.HashMap;

/**
 * 消息工厂
 */
public class MsgHandlerFactory {
    private static final HashMap<MsgCode, MsgHandler> msgHandlers = new HashMap<>();
    static {
        msgHandlers.put(MsgCode.LOGIN_MSG, new loginMsgHandler());
        msgHandlers.put(MsgCode.SINGlE_MSG, new singleMsgHandler());
        msgHandlers.put(MsgCode.GROUP_MSG, new groupMsgHandler());

    }

    public static MsgHandler getHandler(MsgCode code) {
        return msgHandlers.get(code);
    }
}
