package com.relocation.test.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 返回json格式
 */
public class JsonUtil {

    public static JSONObject getJson(){
        JSONObject json = new JSONObject();
        json.put("data", null);
        json.put("meta", null);
        return json;
    }
}

