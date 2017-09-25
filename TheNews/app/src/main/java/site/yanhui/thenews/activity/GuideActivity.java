package site.yanhui.thenews.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import site.yanhui.thenews.R;
import site.yanhui.thenews.adapter.GuideViewpagerAdapter;

/**
 * create at 2017/9/24 by 17:10
 * 作者：Archer
 * 功能描述：
 * splash界面以后的引导界面的设计与开发
 */
public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.splash_viewpager)
    ViewPager splashViewpager; //设置界面
    @BindView(R.id.btn_splash_button)
    Button btnSplashButton;  //进入开始体验的button
    @BindView(R.id.ll_splash_container)
    LinearLayout llSplashContainer; //存放指示器的容器小圆点
    @BindView(R.id.splash_red_point)
    ImageView mSplashRedPoint;   //红点的控件

    private static final String TAG = "GuideActivity";

    private int[] mImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};//引导界面的ID

    public ArrayList<ImageView> imageViewArrayList = new ArrayList<>();//使用集合维护起来
    private int mPointInstance; //两个圆点之间的距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        //隐藏getSupportActionBar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();//隐藏actionBar
        }
        initData();//必须先初始化数据
        splashViewpager.setAdapter(new GuideViewpagerAdapter(imageViewArrayList));//设置Adapter
        splashViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //当页面滑动的过程中回调
                Log.d(TAG, "onPageSelected: 当前位置" +
                        position+"百分比"+positionOffset );
                //计算拿到的距离，总距离乘上百分比，再加上当前的position*一个距离的mPointInstance。
                int marginLeft = (int) (mPointInstance * positionOffset)+position*mPointInstance;//计算距离
                //首先拿到
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSplashRedPoint.getLayoutParams();
                params.leftMargin=marginLeft;//得到的距离设置给mSplashRedPoint控件

                mSplashRedPoint.setLayoutParams(params);//不要忘记了吧参数设置给layoutParams

            }

            @Override
            public void onPageSelected(int position) {
                //某个页面被选中

            }

            @Override
            public void onPageScrollStateChanged(int state) {
               //页面状态发生变化
            }
        });

        //计算两个圆点的距离
        //移动距离=第二个小圆点的left - 第一个小圆点的left
        //先执行oncreate方法，执行完毕以后才可以执行 measure-》layout->draw 所以在oncreate里面写此语句，得到的是0
//        mPointInstance = llSplashContainer.getChildAt(1).getLeft() - llSplashContainer.getChildAt(0).getLeft();
//        Log.d(TAG, "圆点间的距离 "+mPointInstance);

        //监听layout方法事件，位置确定以后，在获取圆点的距离，layout执行完毕，通过回调函数拿到位置
 mSplashRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
     @Override
     public void onGlobalLayout() {
         //移除监听器，避免重复监听
         mSplashRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
         mPointInstance = llSplashContainer.getChildAt(1).getLeft() - llSplashContainer.getChildAt(0).getLeft();
         Log.d(TAG, "圆点间的距离 "+mPointInstance);
     }
 });

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //两种方式
//        for (int id : mImageIds) {
//            ImageView view= new ImageView(this);//拿到imageView对象
//            view.setBackgroundResource(id);//通过设置背景让宽高填充背景
//            imageViewArrayList.add(view);
//
//
//        }
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);//拿到imageView对象
            view.setBackgroundResource(mImageIds[i]);//通过设置背景让宽高填充背景
            imageViewArrayList.add(view);

            //初始化小圆点用shape描述
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);

            //初始化布局参数，宽高包裹内容，父控件是谁，就是谁申明布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);


            if (i > 0) {
                //从第二个点设置左边距
                params.leftMargin = 10;
            }

            point.setLayoutParams(params);//将参数设置给布局

            llSplashContainer.addView(point);//将圆点添加进入到父控件
        }

    }

    //点击事件
    @OnClick(R.id.btn_splash_button)
    public void onViewClicked() {
    }

}
