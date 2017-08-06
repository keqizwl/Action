package com.zwl.baseframe.domain.business.implementz.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by hasee on 2017/8/6.
 */

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static final <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static final <T> T fromJson(String json) {
        return gson.fromJson(json, new TypeToken<T>() {
        }.getType());
    }
}
