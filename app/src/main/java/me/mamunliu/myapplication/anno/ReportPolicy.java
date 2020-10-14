package me.mamunliu.myapplication.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/10/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ReportPolicy {

    String[] collectFields();

    boolean needConvert() default false;
}
