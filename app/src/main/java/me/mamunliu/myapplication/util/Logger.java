package me.mamunliu.myapplication.util;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import me.mamunliu.myapplication.MainActivity;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/27.
 */
public class Logger {

    public static void logReportData(LinkedHashMap<String, String> reportData){
        Log.w(MainActivity.TAG, "ShowResult----------------------ShowResult");
        if (null == reportData) {
            Log.d(MainActivity.TAG, "Result is empty!!!");
            return;
        }
        for (Map.Entry<String, String> data : reportData.entrySet()) {
            Log.w(MainActivity.TAG, data.getKey() + ":" + data.getValue());
        }
    }

}
