package me.mamunliu.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import androidx.appcompat.app.AppCompatActivity;
import me.mamunliu.myapplication.anno.ReportFieldConvert;
import me.mamunliu.myapplication.anno.ReportPolicy;
import me.mamunliu.myapplication.bean.Apple;
import me.mamunliu.myapplication.bean.Brand;
import me.mamunliu.myapplication.bean.ReportPolicyTarget;
import me.mamunliu.myapplication.exam.ExamA;
import me.mamunliu.myapplication.exam.ExamACarrier;
import me.mamunliu.myapplication.util.ArrayUtil;
import me.mamunliu.myapplication.util.Converter;
import me.mamunliu.myapplication.util.Logger;
import me.mamunliu.myapplication.util.Patterns;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ConvertTest";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!ExamACarrier.class.isAnnotationPresent(ExamA.class)) {
            return;
        }
        String url = ExamACarrier.TARGET_URL;
        if (!TextUtils.isEmpty(url)) {
            Matcher matcher = Patterns.URL_PARAMS.matcher(url);
            int groupCount = matcher.groupCount();
            if (groupCount > 1) {
                ExamA examA = ExamACarrier.class.getAnnotation(ExamA.class);
                String[] collectedParams = examA.collectedParams();
                String[] excludedParams = examA.excludedParams();
                StringBuffer stringBuffer = new StringBuffer();
                while (matcher.find()) {
                    String key = matcher.group(1);
                    String value = matcher.group(2);
                    if(ArrayUtil.contains(key, collectedParams)){
                        Log.i(TAG, "collecting key is:" + key + " value is:" + value);
                    }
                    matcher.appendReplacement(stringBuffer, ArrayUtil.contains(key,excludedParams) ? "" : matcher.group());
                }
                Log.i(TAG, "Final:" + stringBuffer.toString());
            }
        }
    }

    private void testMethod() {
        ReportPolicyTarget target = new ReportPolicyTarget();
        if (target.getClass().isAnnotationPresent(ReportPolicy.class)) {
            Log.w(TAG, "ReportPolicy anno is present");
            ReportPolicy reportPolicy = target.getClass().getAnnotation(ReportPolicy.class);
            String[] fields = reportPolicy.collectFields();
            if (fields != null) {
                Log.w(TAG, "fields is present");
                boolean needConvert = reportPolicy.needConvert();
                Log.w(TAG, "needConvert is " + needConvert);
                Map<String, Method> convertMap = null;
                if (needConvert) {
                    convertMap = new HashMap<>();
                    Method[] convertMethods = target.getClass().getDeclaredMethods();
                    for (Method convertMethod : convertMethods) {
                        if (convertMethod.isAnnotationPresent(ReportFieldConvert.class)) {
                            ReportFieldConvert reportFieldConvert = convertMethod.getAnnotation(ReportFieldConvert.class);
                            convertMap.put(reportFieldConvert.fieldName(), convertMethod);
                        }
                    }
                }
                for (String field : fields) {
                    if (convertMap != null && convertMap.containsKey(field)) {
                        Method method = convertMap.get(field);
                        String originVal = "Test" + (field.equals("A") ? "A" : "not A");
                        Object result = null;
                        try {
                            method.setAccessible(true);
                            result = method.invoke(target, originVal);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                            result = originVal;
                        }
                        Log.w(TAG, "final result is " + result.toString());
                    }
                }
            } else {
                Log.w(TAG, "fields is null");
            }
        } else {
            Log.w(TAG, "ReportPolicy anno is not present");
        }
    }

    private void testReportDataConvert() {
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
        stringMap.put("key", new Brand());
        stringMap.put("money", new Brand());
        apple.setStringMap(stringMap);

        Logger.logReportData(TAG, Converter.convertData(apple));
    }

}