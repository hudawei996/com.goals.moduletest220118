package com.goals.personal.debug;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.goals.lib.utils.Config;

import com.goals.personal.PersonalMainActivity;
import com.goals.personal.R;

public class Personal_DebugActivity extends Personal_DebugBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_debug);

        Log.e(Config.TAG, "personal/debug/Personal_DebugActivity");
    }

    public void jump(View view) {
        startActivity(new Intent(this, PersonalMainActivity.class));
    }
}
