package com.shoot.myshootbg.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

public record Restbean<T>(int code, T data, String message) {
    public static <T> Restbean<T> success(T data) {
        return new Restbean<>(200, data, "请求成功");
    }

    public static <T> Restbean<T> unauthorized(String message) {
        return failure(401,message);
    }

    public static <T> Restbean<T> forbidden(String message) {
        return failure(403,message);
    }

    public static <T> Restbean<T> failure(int code,String message) {
        return new Restbean<>(code, null ,message);
    }

    public static <T> Restbean<T> success() {
        return success(null);
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
