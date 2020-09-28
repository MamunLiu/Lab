package me.mamunliu.myapplication.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/27.
 */
public class Logger {

    public static void logReportData(String tag, LinkedHashMap<String, String> reportData) {
        if (null == reportData) {
            Log.d(tag, "ReportData is empty!");
            return;
        }
        Log.w(tag, "The ReportData is as follows:");
        int maxKeyLength = 0;
        for (String key : reportData.keySet()) {
            if (TextUtils.isEmpty(key) || key.length() < maxKeyLength) {
                continue;
            }
            maxKeyLength = key.length()+1;
        }
        for (Map.Entry<String, String> data : reportData.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            String key = data.getKey();
            stringBuilder.append(key).append(' ');
            for (int i = 0; i < maxKeyLength - key.length(); i++) {
                stringBuilder.append('-');
            }
            stringBuilder.append('>').append(' ').append(data.getValue());
            Log.w(tag, stringBuilder.toString());
        }
    }

}
