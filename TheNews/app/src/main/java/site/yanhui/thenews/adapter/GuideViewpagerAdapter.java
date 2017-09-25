package site.yanhui.thenews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archer on 2017/9/24.
 * <p>
 * 功能描述：
 * 为GuideViewpagerAdapter设置一个adapter
 */

public class GuideViewpagerAdapter extends PagerAdapter {


    List<ImageView> imageViewList = new ArrayList<>();//传入一个list集合

    public GuideViewpagerAdapter(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    //Item的个数
    @Override
    public int getCount() {
        return imageViewList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //初始化Item的布局
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViewList.get(position);//获得当前的实例
        container.addView(imageView);
        return imageView;
    }

    //销毁布局
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);    }
}
