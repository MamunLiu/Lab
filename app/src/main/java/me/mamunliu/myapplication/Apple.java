package me.mamunliu.myapplication;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.List;

import androidx.annotation.Keep;

/**
 * Copyright (C) 2020 MamunLiu. All Rights Reserved.
 * Created by MamunLiu on 2020/9/3.
 */
@Keep
class Apple  {
    //原生类型
    private String name;

    //数组
    private int[] point;

    //自定义
    private Brand mBrand= new Brand(){
        @Override
        public String getName() {
            return super.getName();
        }
    };

    private Myinter mMyinter;

    private List<Integer> mylist;

    private double mDoubleVal;

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private Num enumTypeVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPoint() {
        return point;
    }

    public void setPoint(int[] point) {
        this.point = point;
    }

    public Brand getBrand() {
        return mBrand;
    }

    public void setBrand(Brand brand) {
        mBrand = brand;
    }
}
