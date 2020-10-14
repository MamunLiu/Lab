package me.mamunliu.myapplication.bean;

import me.mamunliu.myapplication.anno.ReportFieldConvert;
import me.mamunliu.myapplication.anno.ReportPolicy;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/10/15.
 */
@ReportPolicy(collectFields = {"A", "B"},needConvert = true)
public class ReportPolicyTarget {

    private String name;

    @ReportFieldConvert(fieldName = "A")
    public String convertA(String originStr) {
        return originStr + "A";
    }

    @ReportFieldConvert(fieldName = "B")
    private String convertB(String originStr) {
        return originStr + "B";
    }

}
