package site.yanhui.mobilesafe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import site.yanhui.mobilesafe.R;
import site.yanhui.mobilesafe.other.ConstantValue;
import site.yanhui.mobilesafe.utils.SpUtils;
import site.yanhui.mobilesafe.view.SettingItemView;

public class SettingActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUpdate();

    }

    /**
     * 初始化settingItemView控件
     */
    private void initUpdate() {
        final SettingItemView siv_update = (SettingItemView) findViewById(R.id.siv_update);

        //获取已经有的开关状态，更新app。//默认false
        boolean update = SpUtils.getBoolean(this, ConstantValue.OPEN_UPDATE, false);
        //是否选中，根据上次的结果做判断。
        siv_update.setCheck(update);

        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先拿到默认的状态
                boolean check = siv_update.isCheck();//拿到默认选中状态
               //设置取反
                siv_update.setCheck(!check);//设置为取反

                //把点击时间传入config里面，下次再打开的时候直接读取
                SpUtils.putBoolean(getApplicationContext(),ConstantValue.OPEN_UPDATE,!check);
            }
        });
    }



}
