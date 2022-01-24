package com.goals.personal;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// personal个人
public class Personal_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);

        // Order_Main

        // todo 方式二 全局Map
        /*Class<?> targetActivity =
                RecordPathManager.startTargetActivity("order", "Order_MainActivity");
        startActivity(new Intent(this, targetActivity));*/
    }

    public void jumpApp(View view) {

    }

    public void jumpOrder(View view) {

    }
}
