package site.yanhui.thenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.yanhui.thenews.activity.GuideActivity;
import site.yanhui.thenews.activity.MainActivity;
import site.yanhui.thenews.others.ConstantValues;
import site.yanhui.thenews.utils.SpUtils;

/**
 * create at 2017/9/24 by 17:10
 * 作者：Archer
 * 功能描述：
 * 闪屏界面的开发，放了三个动画进入到了动画集合。
 */
public class SplashActivity extends AppCompatActivity {


    /*
    splash的背景图片小马
     */
    @BindView(R.id.image_view)
    ImageView imageView;
    /*
    跟布局的id
     */
    @BindView(R.id.SplashRoot)
    LinearLayout SplashRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

         // 旋转动画
        RotateAnimation animRotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animRotate.setDuration(1000);// 动画时间
        animRotate.setFillAfter(true);// 保持动画结束状态

        // 缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);// 保持动画结束状态

        // 渐变动画
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(2000);// 动画时间
        animAlpha.setFillAfter(true);// 保持动画结束状态

        //用动画集合运行全部动画
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        /*
       跟布局进入动画的东晓
        */
        SplashRoot.startAnimation(set);

        //为动画设置监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束，跳转页面.
                //如果是第一次进入那么就跳转到新手引导界面
                //否则就是直接使用
                //先从我们的config配置文件中取出对应的sp值，默认是true，如果是false，说明已经登陆过，就不需要在
                //进入导航页面了
                boolean firstCome = SpUtils.getBoolean(SplashActivity.this, ConstantValues.FIRST_COME, true);
                Intent intent;
                if (firstCome) {//如果为真，表示第一次进入
                    intent = new Intent(getApplicationContext(), GuideActivity.class);
                    //跳转到导航页面以后，把值设置为false 下次就进入了主页面
//                    SpUtils.putBoolean(getApplicationContext(),ConstantValues.FIRST_COME,false);

                } else {//为假
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                }
                finish();//结束掉闪屏界面
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}


