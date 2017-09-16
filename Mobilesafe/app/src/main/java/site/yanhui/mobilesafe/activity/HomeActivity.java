package site.yanhui.mobilesafe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import site.yanhui.mobilesafe.R;
import site.yanhui.mobilesafe.utils.AppManagerUtils;

public class HomeActivity extends AppCompatActivity {

    private AppManagerUtils manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        manager = AppManagerUtils.getAppManager();
        manager.addActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        manager.finishAllActivity();
    }
}
