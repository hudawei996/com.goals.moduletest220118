package com.goals.lib.base;

import android.app.Application;
import android.util.Log;

import com.goals.lib.utils.Config;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Config.TAG, "common/BaseApplication onCreate run");
    }
}
