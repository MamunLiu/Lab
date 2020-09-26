package me.mamunliu.myapplication;

import android.os.Bundle;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ConvertTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Apple apple = new Apple();
        apple.setName("Fuji");
        apple.setPoint(new int[]{88, 99, 100});

        showResult(Converter.convertData(apple));

    }

    private void measureNormal(Apple apple) {
        long startTS = System.currentTimeMillis();
        for (int i = 0;i<1000000;i++){
            LinkedHashMap<String, String> reportData = new LinkedHashMap<>();
            if (apple != null) {
                if (apple.getName() != null) {
                    reportData.put("name", apple.getName());
                }
                if (apple.getPoint() != null) {
                    reportData.put("points", apple.getPoint().toString());
                }
                if (apple.getBrand() != null) {
                    reportData.put("brand", apple.getBrand().toString());
                }
            }
        }
        long endTS = System.currentTimeMillis();
        Log.w(TAG, "measureNormal--------" + (endTS - startTS));
    }

    private void measureReflect(Apple apple) {
        long startTS = System.currentTimeMillis();
        for (int i = 0;i<1000000;i++){
            LinkedHashMap<String, String> reportData = Converter.convertData(apple);
        }
        long endTS = System.currentTimeMillis();
        Log.w(TAG, "measureReflect--------" + (endTS - startTS));
    }


    private void showResult(LinkedHashMap<String, String> reportData) {
        Log.w(TAG, "ShowResult----------------------ShowResult");
        if (null == reportData) {
            Log.d(TAG, "Result is empty!!!");
            return;
        }
        for (Map.Entry<String, String> data : reportData.entrySet()) {
            Log.w(TAG, data.getKey() + ":" + data.getValue());
        }
    }


}