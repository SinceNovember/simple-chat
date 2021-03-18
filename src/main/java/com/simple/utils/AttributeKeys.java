package com.simple.utils;

import io.netty.util.AttributeKey;

public class AttributeKeys {
    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    public static AttributeKey<String> USER_ID = AttributeKey.newInstance("userId");

}
