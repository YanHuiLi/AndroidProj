package site.yanhui.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import site.yanhui.mobilesafe.R;

/**
 * Created by Archer on 2017/9/19.
 * <p>
 * 功能描述：
 * 自定义组合控件的使用
 * 将我们抽取出来的setting_item_view进行加载处理
 * xml转成view
 */

public class SettingItemView extends LinearLayout{

    private static final String TAG = "SettingItemView";
    private  static  final  String  NAMESPACE = "http://schemas.android.com/apk/res/site.yanhui.mobilesafe";
    private CheckBox setting_ck_box;
    private TextView tv_setting_des;


    private String mDesTitle;
    private String mDesOn;
    private String mDesOff;
    private TextView tv_setting_title;

    public SettingItemView(Context context) {
        this(context,null);//调用第二个构造
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);//调用第三个构造
    }

    /**
     *
     * @param context 上下文环境
     * @param attrs    属性
     * @param defStyleAttr 默认的样式
     */
    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //xml---》view
        /**
         * 解释一下 View.inflate的三个构造的含义
         * 1.上下文环境
         * 2.抽取出来的布局文件，需要转化成为view
         * 3.转化的出来的view挂载在SettingItemView上，才能展示。所以传入this
         * 4.再把SettingItemView对应view对象放入SettingActivity里面
         */
        View.inflate(context,R.layout.setting_item_view,this);

        //等效的
//        View view = View.inflate(context, R.layout.setting_item_view, null);//也可以不挂载
//         this.addView(view);//还是加入进去，不然无法显示

        //执行完inflate代码以后就可以找到控件，复用了
        tv_setting_title = (TextView) this.findViewById(R.id.tv_setting_title);
        tv_setting_des = (TextView) this.findViewById(R.id.tv_setting_des);
        setting_ck_box = (CheckBox) this.findViewById(R.id.setting_ck_box);

        //1 获取自定义以及原生属性的操作
        initAttrs(attrs);
        //2 初始化完成了以后才可以赋值,获取标题
        tv_setting_title.setText(mDesTitle);


//        setting_ck_box.isChecked();//判断是否选中，选中返回true

    }

    /**
     * 拿到自定义方法中的自定义属性
     * @param set  构造方法中维护好的属性自合
     */
    private void initAttrs(AttributeSet set) {

        //获取属性的总个数
        Log.i(TAG, "initAttrs: "+set.getAttributeCount());
        //获取所有属性名称和属性值
//        for (int i = 0; i < set.getAttributeCount(); i++) {
//            //id拿到的是在R中的索引值
//            Log.i(TAG, "initAttrs: name is "+  set.getAttributeName(i));
//            Log.i(TAG, "initAttrs:  value is "+  set.getAttributeValue(i));
//            Log.i(TAG, "initAttrs: =================================");


        mDesTitle = set.getAttributeValue(NAMESPACE, "desTitle");
        mDesOn = set.getAttributeValue(NAMESPACE, "desOn");
        mDesOff = set.getAttributeValue(NAMESPACE, "desOff");
        Log.i(TAG, "initAttrs: desTitle " + mDesTitle);
        Log.i(TAG, "initAttrs: desOn " + mDesOn);
        Log.i(TAG, "initAttrs: desOff " + mDesOff);
        }



    /**
     *判断checkBox的选中状态来决定SettingItemView的选中状态
     * @return 返回当前的SettingItemView是否是选中状态
     */
    public boolean isCheck(){
        return setting_ck_box.isChecked();//返回的是checkbox的状态
    }

    /**
     * @param isCheck  是否作为开启的变量，由为我们点击过程中去切换
     */
    public void setCheck(boolean isCheck){
        //当前条目在选中的过程中，setting_ck_box跟随变化
        setting_ck_box.setChecked(isCheck);
        if (isCheck){
            tv_setting_des.setText(mDesOn);
        }else {
            tv_setting_des.setText(mDesOff);
        }
    }
}
