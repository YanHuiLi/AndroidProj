package site.yanhui.thenews.fragment;

import android.view.View;

import site.yanhui.thenews.R;

/**
 * Created by Archer on 2017/9/28.
 * <p>
 * 功能描述： 右边栏目的展示，主页面的项目展示
 */

public class ContentFragment extends BaseFragment {
    @Override
    public View initData() {


        return null;
    }

    @Override
    public View initView() {
//加载布局
        View inflate = View.inflate(mActivity, R.layout.fragment_content, null);
        return inflate;
    }
}
