package me.mamunliu.myapplication.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import me.mamunliu.myapplication.anno.ReportName;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/14.
 */
public class Converter {

    public static LinkedHashMap<String, String> convertData(Object tarInstance) {
        if (null == tarInstance) {
            return null;
        }
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Class<?> clz = tarInstance.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
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
            if (null == reportName) {
                reportName = field.getName();
            }
            result.put(reportName, processFieldVal(field.getType(), fieldVal));
        }
        return result;
    }

    private static String processFieldVal(Class<?> type, Object fieldVal) {
        if (null == fieldVal) {
            return "null";
        }
        if (type.isArray()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            int arrayLength = Array.getLength(fieldVal);
            for (int i = 0; i < arrayLength; i++) {
                Object val = Array.get(fieldVal, i);
                stringBuilder.append(val == null ? "null" : processFieldVal(val.getClass(), val));
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
}
