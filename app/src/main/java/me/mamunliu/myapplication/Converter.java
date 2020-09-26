package me.mamunliu.myapplication;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/14.
 */
class Converter {

    public static LinkedHashMap<String, String> convertData(Object tarInstance) {
        if (null == tarInstance) {
            return null;
        }
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Class<?> clz = tarInstance.getClass();
        Class<?>[] classes=clz.getDeclaredClasses();
        for (Class<?> cl:classes){
            Log.w(MainActivity.TAG,cl.getSimpleName()+"---------:----------------");
        }
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object fieldVal = null;
            Log.w(MainActivity.TAG,field.getName()+"isPrimitive:"+field.getType().isPrimitive());
            Log.w(MainActivity.TAG,field.getName()+"isAnnotation:"+field.getType().isAnnotation());
            Log.w(MainActivity.TAG,field.getName()+"isAnonymousClass:"+field.getType().isAnonymousClass());
            Log.w(MainActivity.TAG,field.getName()+"isArray:"+field.getType().isArray());
            Log.w(MainActivity.TAG,field.getName()+"isEnum:"+field.getType().isEnum());
            Log.w(MainActivity.TAG,field.getName()+"isInterface:"+field.getType().isInterface());
            Log.w(MainActivity.TAG,field.getName()+"isLocalClass:"+field.getType().isLocalClass());
            Log.w(MainActivity.TAG,field.getName()+"isMemberClass:"+field.getType().isMemberClass());
            try {
                fieldVal = field.get(tarInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldVal != null) {
                result.put(field.getName(), fieldVal.toString());
            }
        }
        return result;
    }
}
