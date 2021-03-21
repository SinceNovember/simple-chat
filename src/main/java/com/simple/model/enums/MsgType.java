package com.simple.model.enums;

public enum MsgType implements ValueEnum<Integer>{
    TEXT(1,"文本"),
    PIC(2,"图片");


    private final Integer value;
    private final String msg;

    MsgType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return value;

    }

    public String getMsg() {
        return msg;
    }
}
