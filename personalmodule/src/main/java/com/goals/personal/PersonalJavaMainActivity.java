package com.goals.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goals.arouter_annotations.ARouter;
import com.goals.lib.RecordPathManager;

@ARouter(path = "personal/PersonalJavaMainActivity")
public class PersonalJavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_java_main);
    }

    public void javaJumpLogin(View view) {
        //todo 方式一 类加载
        //        try {
        //            var targetClass = Class.forName("com.goa
        //            var intent = Intent(this,targetClass)
        //            startActivity(intent)
        //        } catch (e: Exception) {
        //            e.printStackTrace()
        //        }
        //todo 方式二 全局map
        Class<?> targetActivity = RecordPathManager.startTargetActivity("login","LoginJavaMainActivity");
        startActivity(new Intent(this,targetActivity));
    }
}