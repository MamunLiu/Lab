package me.mamunliu.myapplication;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import me.mamunliu.myapplication.bean.Apple;
import me.mamunliu.myapplication.util.Converter;
import me.mamunliu.myapplication.util.Logger;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ConvertTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Apple apple = new Apple();
        apple.setName("Fuji");
        List<String> stringList = new ArrayList<>();
        stringList.add("A apple");
        stringList.add("taste good");
        apple.setDescInfo(stringList);

        Logger.logReportData(Converter.convertData(apple));
    }



}