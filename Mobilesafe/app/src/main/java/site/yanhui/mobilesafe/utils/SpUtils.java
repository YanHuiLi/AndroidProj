package site.yanhui.mobilesafe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Archer on 2017/9/19.
 * <p>
 * 功能描述：
 */

public class SpUtils {

    private static SharedPreferences sp;

    /**
     *
     * @param context  上下文环境
     * @param key      config里面的节点名称
     * @param value     存入的值
     */
    public static  void  putBoolean(Context context, String key,boolean value){

        if (sp==null){
            //拿到一个叫做config的配置文件，设置为只有该调用程序可以读取的状态
            //其实我们平时调用的系统服务，都默认了一个this，上下文环境，所以要写成context.de形式
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();//拿到可编辑模式
        edit.putBoolean(key,value).apply();//传入数据
    }

    /**
     *
     * @param context  上下文
     * @param key    节点的名称
     * @param defValue  默认值
     * @return  如果有值的话，返回值
     */
    public static boolean getBoolean(Context context , String key, boolean defValue){
        if (sp==null){
            //拿到一个叫做config的配置文件，设置为只有该调用程序可以读取的状态
            //其实我们平时调用的系统服务，都默认了一个this，上下文环境，所以要写成context.de形式
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
         return sp.getBoolean(key,defValue );
    }



    /**
     *
     * @param context  上下文环境
     * @param key      config里面的存储节点
     * @param value     存储节点的值
     */
    public static  void  putString(Context context, String key,String value){

        if (sp==null){
            //拿到一个叫做config的配置文件，设置为只有该调用程序可以读取的状态
            //其实我们平时调用的系统服务，都默认了一个this，上下文环境，所以要写成context.de形式
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sp.edit();//拿到可编辑模式
        edit.putString(key,value).apply();//传入数据
    }

    /**
     *
     * @param context  上下文
     * @param key    节点的名称
     * @param defValue  默认值
     * @return  如果有值的话，返回值
     */
    public static String getString(Context context , String key, String defValue){
        if (sp==null){
            //拿到一个叫做config的配置文件，设置为只有该调用程序可以读取的状态
            //其实我们平时调用的系统服务，都默认了一个this，上下文环境，所以要写成context.de形式
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue );
    }
}
