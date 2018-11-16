package com.zhangongjx.infrastructure.util;

import java.util.Base64;

public class Base64Helper {
    public  static  String Base64(Object str)
    {
        Base64.Encoder encoder = Base64.getEncoder();
        String text = (String) str;
        byte[] bytes = text.getBytes();
        return encoder.encodeToString(bytes);
    }
}
