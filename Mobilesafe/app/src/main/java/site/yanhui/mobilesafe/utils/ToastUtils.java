package site.yanhui.mobilesafe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Archer on 2017/9/16.
 * <p>
 * 功能描述：
 * 一个弹出土司的工具类
 */

public class ToastUtils {

    public static void showShort(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }


    public static void showLong(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

}
