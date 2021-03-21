package com.simple.model.enums;

public enum MsgReadType  implements ValueEnum<Integer>{
    IS_READ(1,"已读"),
    NOT_READ(0,"为读");


    private final Integer value;
    private final String msg;

    MsgReadType(Integer value, String msg) {
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
