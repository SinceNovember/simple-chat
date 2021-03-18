package com.simple.server.handler.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ToString
public class Msg {
    private MsgCode type;

    private String toId;

    private String fromId;

    private String content;

    public MsgCode getType() {
        return type;
    }

    public void setType(int type) {
        this.type = MsgCode.getMsgCode(type);
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }






}
