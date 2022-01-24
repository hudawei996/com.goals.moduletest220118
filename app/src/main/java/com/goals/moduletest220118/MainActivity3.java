package com.goals.moduletest220118;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.goals.arouter_annotations.ARouter;
import com.goals.loginmodule.LoginJavaMainActivity;
import com.goals.personal.PersonalJavaMainActivity;

@ARouter(path="/app/MainActivity3")
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 所以不同的模块，xml页面名称最好是唯一的
        setContentView(R.layout.activity_main);

        boolean serverUrl = BuildConfig.DEBUG;

        //主项目可以不通过路由，直接跳转到各个子项目的页面，因为他依赖了所有的项目，可以不用路由表来找
        //子项目中跳转到 隔壁项目 互不依赖 需要通过主项目中的路由表 subsequent
    }

    public void jumpLogin(android.view.View view) {
        Intent intent = new Intent(this, LoginJavaMainActivity.class);
        intent.putExtra("name", "goals");
        startActivity(intent);
    }

    public void jumpPersonal(android.view.View view) {
        Intent intent = new Intent(this, PersonalJavaMainActivity.class);
        intent.putExtra("name", "goals");
        startActivity(intent);
    }
}