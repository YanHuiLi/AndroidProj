package site.yanhui.thenews.activity;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import site.yanhui.thenews.R;

/**
*create at 2017/9/25 by 15:30
*作者：Archer
*功能描述：
 * 导入第三方的slideMenu库
 * 然后继承自SlidingFragmentActivity就可以使用侧边栏
*/
public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置左侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu=getSlidingMenu();
        //全部可以使用
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(200);//预留200像素


    }
}
