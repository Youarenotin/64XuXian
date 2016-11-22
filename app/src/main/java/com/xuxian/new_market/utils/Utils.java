package com.xuxian.new_market.utils;

import com.ab.http.AbRequestParams;

import java.util.Map;

/**
 * Created by youarenotin on 2016/11/11.
 */

public class Utils {

    public static String getTimeStampString(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getRequestToken(Map<String, String> paramsMap) {
        AbRequestParams abMap = new AbRequestParams();
        for (Map.Entry entry : paramsMap.entrySet()) {
            abMap.put((String) (entry.getKey()),(String) entry.getValue());
        }
        return  abMap.getMd5().isEmpty()|| abMap.getMd5()==null?null:abMap.getMd5();
    }
}
