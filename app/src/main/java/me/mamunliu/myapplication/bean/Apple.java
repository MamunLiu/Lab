package me.mamunliu.myapplication.bean;

import java.util.List;

import androidx.annotation.Keep;
import me.mamunliu.myapplication.anno.NotReported;
import me.mamunliu.myapplication.anno.ReportName;


/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/3.
 */
@Keep
public class Apple {

    private String name;

    private double price;

    @NotReported
    @ReportName(value = "brand")
    private Brand mBrand;

    private List<String> descInfo;

    private String[] providerName = {"TigerMarket", "BambooMarket"};

    private Color mColor = Color.GREEN;

    private int num = 100;

    private String[][] ok= {{"1","2"},{"a","b"}};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return mBrand;
    }

    public void setBrand(Brand brand) {
        mBrand = brand;
    }

    public List<String> getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(List<String> descInfo) {
        this.descInfo = descInfo;
    }
}
