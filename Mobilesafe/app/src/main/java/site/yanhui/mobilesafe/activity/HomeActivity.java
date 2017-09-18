package site.yanhui.mobilesafe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import site.yanhui.mobilesafe.Adapter.FunctionAdapter;
import site.yanhui.mobilesafe.R;
import site.yanhui.mobilesafe.bean.FunctionBean;
import site.yanhui.mobilesafe.other.SpaceItemDecoration;
import site.yanhui.mobilesafe.utils.AppManagerUtils;

public class HomeActivity extends AppCompatActivity {

    private AppManagerUtils manager;
    private RecyclerView rv_function;
    private List<FunctionBean> functionBeanList=new ArrayList<>();
    public FunctionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        manager = AppManagerUtils.getAppManager();
        manager.addActivity(this);

        //初始化我们准备好的功能模块
        initFunctionBean();
        //初始化组件
        initUI();

        //1.网格布局管理器，三列的
        GridLayoutManager gridLayoutManager= new GridLayoutManager(HomeActivity.this,3);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        //2.设置网格布局管理器
        rv_function.setLayoutManager(gridLayoutManager);
        //3.添加边距
        rv_function.addItemDecoration(new SpaceItemDecoration(3,50,false));//
        //4.初始化适配器
        mAdapter = new FunctionAdapter(functionBeanList);
        //设置是适配器
        rv_function.setAdapter(mAdapter);

        //注册单个条目的点击时间

    }

    /**
     * 初始化我们准备好的功能模块
     */
    private void initFunctionBean() {

        FunctionBean bean = new FunctionBean("手机防盗", R.drawable.home_safe);
        functionBeanList.add(bean);

        FunctionBean bean1 = new FunctionBean("通讯卫士", R.drawable.home_callmsgsafe);
        functionBeanList.add(bean1);

        FunctionBean bean2 = new FunctionBean("软件管理", R.drawable.home_apps);
        functionBeanList.add(bean2);

        FunctionBean bean3 = new FunctionBean("进程管理", R.drawable.home_taskmanager);
        functionBeanList.add(bean3);

        FunctionBean bean4 = new FunctionBean("流量统计", R.drawable.home_netmanager);
        functionBeanList.add(bean4);

        FunctionBean bean5 = new FunctionBean("手机杀毒", R.drawable.home_trojan);
        functionBeanList.add(bean5);

        FunctionBean bean6 = new FunctionBean("缓存清理", R.drawable.home_sysoptimize);
        functionBeanList.add(bean6);

        FunctionBean bean7 = new FunctionBean("高级工具", R.drawable.home_tools);
        functionBeanList.add(bean7);

        FunctionBean bean8 = new FunctionBean("设置中心", R.drawable.home_settings);
        functionBeanList.add(bean8);

    }

    /**
     * 初始化组件
     */
    private void initUI() {
        rv_function = (RecyclerView) findViewById(R.id.rv_function);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        manager.finishAllActivity();
    }
}
