package com.goals.personal.debug;

import android.os.Bundle;
import android.util.Log;
import com.goals.lib.utils.Config;
import androidx.appcompat.app.AppCompatActivity;

public class Personal_DebugBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(Config.TAG, "personal/debug/Personal_DebugBaseActivity");
    }
}
