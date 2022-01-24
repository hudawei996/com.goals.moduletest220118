package com.goals.moduletest220118;

import android.app.Application;

import com.goals.lib.RecordPathManager;
import com.goals.loginmodule.LoginJavaMainActivity;
import com.goals.personal.PersonalJavaMainActivity;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //如果项目有100个Activity，这种加法会不会太繁琐
        RecordPathManager.addGroupInfo("app","MainActivity",
                MainActivity.class);
        RecordPathManager.addGroupInfo("login","LoginJavaMainActivity",
                LoginJavaMainActivity.class);
        RecordPathManager.addGroupInfo("personal","PersonalJavaMainActivity",
                PersonalJavaMainActivity.class);
    }
}
