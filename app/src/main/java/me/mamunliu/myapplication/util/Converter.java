package me.mamunliu.myapplication.util;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import me.mamunliu.myapplication.anno.NotReported;
import me.mamunliu.myapplication.anno.ReportName;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/14.
 */
public class Converter {

    public static LinkedHashMap<String, String> convertData(Object tarInstance) {
        return convertData(tarInstance, new ConvertListenerDefImpl());
    }

    public static LinkedHashMap<String, String> convertData(Object tarInstance, ConvertListener convertListener) {
        if (null == tarInstance) {
            return null;
        }
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        if (null != convertListener) {
            convertListener.onConvertStart(result);
        }
        Class<?> clz = tarInstance.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if (field.isAnnotationPresent(NotReported.class)) {
                continue;
            }
            Object fieldVal = null;
            try {
                fieldVal = field.get(tarInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String reportName = null;
            if (field.isAnnotationPresent(ReportName.class)) {
                ReportName reportNameVal = field.getAnnotation(ReportName.class);
                reportName = reportNameVal.value();
            }
            if (TextUtils.isEmpty(reportName)) {
                reportName = field.getName();
            }
            String reportVal;
            if (null != convertListener) {
                reportVal = convertListener.onConvertFiled(reportName, field.getType(), fieldVal);
            } else {
                reportVal = String.valueOf(fieldVal);
            }
            result.put(reportName, reportVal);
        }
        if (null != convertListener) {
            convertListener.onConvertFinish(result);
        }
        return result;
    }

    interface ConvertListener {
        void onConvertStart(LinkedHashMap<String, String> reportData);

        String onConvertFiled(String reportName, Class<?> type, Object fieldVal);

        void onConvertFinish(LinkedHashMap<String, String> reportData);
    }
}
