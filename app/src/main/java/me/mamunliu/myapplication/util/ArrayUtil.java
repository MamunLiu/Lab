package me.mamunliu.myapplication.util;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/10/25.
 */
public class ArrayUtil {

    public static <T> boolean contains(T tar, T... array) {
        if (tar == null || array == null){
            return false;
        }
        for (T t : array) {
            if (t instanceof String && ((String) t).equalsIgnoreCase((String) tar)) {
                return true;
            } else if (t.equals(tar)) {
                return true;
            }
        }
        return false;
    }
}
