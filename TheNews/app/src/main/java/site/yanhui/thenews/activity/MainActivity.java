package site.yanhui.thenews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import site.yanhui.thenews.R;
import site.yanhui.thenews.fragment.ContentFragment;
import site.yanhui.thenews.fragment.LeftMenuFragment;

/**
*create at 2017/9/25 by 15:30
*作者：Archer
*功能描述：
 * 导入第三方的slideMenu库
 * 然后继承自SlidingFragmentActivity就可以使用侧边栏
*/
public class MainActivity extends SlidingFragmentActivity {


    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置左侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu=getSlidingMenu();
        //全部可以使用
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(800);//设置屏幕的预留800像素，拉开菜单以后,屏幕还有800像素

        initFragment();
    }

    /**
     * 动态加载fragment布局
     * 初始化fragment
     */
    private void initFragment() {

        FragmentManager manager = getSupportFragmentManager();//拿到fragment的管理器
        FragmentTransaction transaction = manager.beginTransaction();//开启事物机制
        transaction.replace(R.id.cl_activity,new ContentFragment());//param1:带替换的根布局id param2：替换的fragment。
        transaction.replace(R.id.fl_left_Menu,new LeftMenuFragment());//第一个参数是根布局容器的id，第二个参数是待替换的fragment


        transaction.commit();//提交改变

        //fragment Tag的使用，在创建的时候，为fragment指定一个stringTag，然后可以通过manager的findFragmentByTag方法找到
//        transaction.replace(R.id.cl_activity,new ContentFragment(),TAG_LEFT_MENU);
//        Fragment fragment = manager.findFragmentByTag(TAG_CONTENT_FRAGMENT);

    }


}
