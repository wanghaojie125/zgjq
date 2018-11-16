package com.zhangongjx.infrastructure.util;


import com.alibaba.fastjson.JSON;

public class JsonHelper {

    public static <T> String toJSON(T item) {
        return JSON.toJSONString(item);
    }

    public static <T> T toObject(String string, Class<T> tClass) {
        T item = JSON.parseObject(string, tClass);
        return item;
    }
}
