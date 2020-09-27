package me.mamunliu.myapplication.util;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/28.
 */
public class ConvertListenerDefImpl implements Converter.ConvertListener {
    @Override
    public void onConvertStart(LinkedHashMap<String, String> reportData) {
    }

    @Override
    public String onConvertFiled(String reportName, Class<?> type, Object fieldVal) {
        if (null == fieldVal) {
            return "null";
        }
        if (type.isArray()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            int arrayLength = Array.getLength(fieldVal);
            for (int i = 0; i < arrayLength; i++) {
                Object val = Array.get(fieldVal, i);
                stringBuilder.append(val == null ? "null" : onConvertFiled(reportName, val.getClass(), val));
                if ((i + 1) == arrayLength) {
                    break;
                }
                stringBuilder.append(',').append(' ');
            }
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
        return String.valueOf(fieldVal);
    }

    @Override
    public void onConvertFinish(LinkedHashMap<String, String> reportData) {
    }
}
