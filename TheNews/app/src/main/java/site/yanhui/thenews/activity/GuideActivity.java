package site.yanhui.thenews.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import site.yanhui.thenews.R;
import site.yanhui.thenews.adapter.GuideViewpagerAdapter;

/**
*create at 2017/9/24 by 17:10
*作者：Archer
*功能描述：
 * splash界面以后的引导界面的设计与开发
*/
public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.splash_viewpager)
    ViewPager splashViewpager; //设置界面
    @BindView(R.id.btn_splash_button)
    Button btnSplashButton;  //进入开始体验的button
    @BindView(R.id.ll_splash_container)
    LinearLayout llSplashContainer; //存放指示器的容器

    private  int[] mImageIds=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};//引导界面的ID

   public ArrayList<ImageView> imageViewArrayList= new ArrayList<>();//使用集合维护起来

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

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //两种方式
        for (int id : mImageIds) {
            ImageView view= new ImageView(this);//拿到imageView对象
            view.setBackgroundResource(id);//通过设置背景让宽高填充背景
            imageViewArrayList.add(view);
        }
//        for (int i = 0; i < mImageIds.length; i++) {
//            ImageView view= new ImageView(this);//拿到imageView对象
//            view.setBackgroundResource(mImageIds[i]);//通过设置背景让宽高填充背景
//            imageViewArrayList.add(view);
//        }

    }

    //点击事件
    @OnClick(R.id.btn_splash_button)
    public void onViewClicked() {
    }

}
