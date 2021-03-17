package com.simple.server.handler.message;

import java.util.HashMap;

/**
 * 消息工厂
 */
public class MsgHandlerFactory {
    private static final HashMap<String, MsgHandler> msgHandlers = new HashMap<>();
    static {
        msgHandlers.put(Command.SINGLE_SEND, new singleMsgHandler());
    }

    public static MsgHandler getHandler(String type) {
        return msgHandlers.get(type);
    }
}
