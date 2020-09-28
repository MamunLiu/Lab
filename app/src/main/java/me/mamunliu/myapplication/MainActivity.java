package me.mamunliu.myapplication;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import me.mamunliu.myapplication.bean.Apple;
import me.mamunliu.myapplication.bean.Brand;
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
        Set<String> stringSet = new HashSet<>();
        stringSet.add("gulu");
        stringSet.add("treasure");
        stringSet.add("ring");
        apple.setStringSet(stringSet);
        Map<String, Brand> stringMap = new LinkedHashMap<>();
        stringMap.put("key",new Brand());
        stringMap.put("money",new Brand());
        apple.setStringMap(stringMap);

        Logger.logReportData(Converter.convertData(apple));
    }



}