package site.yanhui.mobilesafe.xUtils3;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Archer on 2017/9/16.
 * <p>
 * 功能描述：
 * 初始化 xutils3
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
