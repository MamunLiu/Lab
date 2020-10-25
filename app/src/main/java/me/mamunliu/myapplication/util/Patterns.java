package me.mamunliu.myapplication.util;

import java.util.regex.Pattern;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/10/25.
 */
public class Patterns {

    private Patterns() {
    }

    public static final String URL_PARAMS_STR = "(?<=\\??)(\\w+)=([^&]*)";

    public static final Pattern URL_PARAMS = Pattern.compile(URL_PARAMS_STR);
}
