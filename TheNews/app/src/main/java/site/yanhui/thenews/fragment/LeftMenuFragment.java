package site.yanhui.thenews.fragment;

import android.view.View;

import site.yanhui.thenews.R;

/**
 * Created by Archer on 2017/9/28.
 * <p>
 * 功能描述： 左边栏目的展示
 */

public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initData() {
        return null;
    }

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);

        return view;

    }
}
