package site.yanhui.thenews.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Archer on 2017/9/28.
 * <p>
 * 功能描述：
 * 设计一个fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    public Activity mActivity;

    //创建fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();//获取当前fragment所依赖的activity
    }


    //初始化一个fragment的布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    //fragment所依赖的activity的oncreate方法执行结束
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        initData();
    }

    //初始化数据
    public abstract void initData();

    //初始化布局，必须由子类实现,多态机制决定了，调用子类实现的子类
    public abstract View initView();
}
