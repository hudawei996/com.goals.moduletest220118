package com.goals.personal.debug;

import android.app.Application;
import android.util.Log;
import com.goals.lib.utils.Config;

public class Personal_DebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Config.TAG, "personal/debug/Personal_DebugApplication");
    }
}
