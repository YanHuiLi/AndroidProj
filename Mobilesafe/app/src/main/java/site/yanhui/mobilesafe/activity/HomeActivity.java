package site.yanhui.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import site.yanhui.mobilesafe.Adapter.FunctionAdapter;
import site.yanhui.mobilesafe.R;
import site.yanhui.mobilesafe.bean.FunctionBean;
import site.yanhui.mobilesafe.other.ConstantValue;
import site.yanhui.mobilesafe.other.SpaceItemDecoration;
import site.yanhui.mobilesafe.utils.AppManagerUtils;
import site.yanhui.mobilesafe.utils.SpUtils;
import site.yanhui.mobilesafe.utils.ToastUtils;

public class HomeActivity extends AppCompatActivity {

    private AppManagerUtils manager;
    private RecyclerView rv_function;
    private List<FunctionBean> functionBeanList=new ArrayList<>();
    public FunctionAdapter mAdapter;
    private String psd;

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

//        1.网格布局管理器，三列的
        GridLayoutManager gridLayoutManager= new GridLayoutManager(HomeActivity.this,3);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

//        2.设置网格布局管理器
        rv_function.setLayoutManager(gridLayoutManager);
//        3.添加边距
        rv_function.addItemDecoration(new SpaceItemDecoration(3,50,false));//
//        4.初始化适配器
        mAdapter = new FunctionAdapter(functionBeanList);
        mAdapter.setOnItemClickLitener(new FunctionAdapter.OnItemClicklistener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {

                    case 0:
                        showDialog();
                        break;
                    case 8:
                        Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //没使用这个方法
            }
        });
//        设置是适配器
        rv_function.setAdapter(mAdapter);
    }


    private void showDialog() {
        //判断本地是否有密码
        psd = SpUtils.getString(this, ConstantValue.MOBILE_SAFE_PSD, "");
        //1.初始设置的密码对话款
        if (TextUtils.isEmpty(psd)) {
           showSetPsdDialog();
        }
        else{
        //2.确认密码的对话款
            showConfirmPsdDialog();
        }

    }

    /**
     * 如果有密码了，就走确认的逻辑
     * 因为在mAlertDialog存储了第一次设置的密码
     */
    private void showConfirmPsdDialog() {
        //得到alertDialog的实例
        //1，使用的builder对象的.create()方法来构造的
        AlertDialog.Builder alertDialogBuilder =new AlertDialog.Builder(this);
        final AlertDialog alertDialog = alertDialogBuilder.create();
       //xml->转inflate
        View inflate = View.inflate(HomeActivity.this, R.layout.dialog_confirm_password, null);
        alertDialog.setView(inflate);
        //拿到组件
        final EditText et_input_psd = (EditText) inflate.findViewById(R.id.et_input_psd);
        Button btn_confirm_submit = (Button) inflate.findViewById(R.id.btn_confirm_submit);
        Button btn_confirm_cancel = (Button) inflate.findViewById(R.id.btn_confirm_cancel);
       //show出来
        alertDialog.show();

        //点击取消的时候，设置监听事件
        btn_confirm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 只有dialog才可以取消的方法
                 */
                alertDialog.dismiss();
            }
        });

        //点击确定的时候，跳装到setUpActivity
        btn_confirm_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etInputPsd = et_input_psd.getText().toString().trim();
                if (etInputPsd.equals(psd)) {
                    Intent intent = new Intent(HomeActivity.this, SetupActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }else {
                    ToastUtils.showShort(getApplicationContext(),"输入的密码有误，请重新输入");
                    et_input_psd.setText("");
                }
            }
        });
    }

    /**
     * 初次设置对话框
     */
    private void showSetPsdDialog() {
        //自己定义对话框的展示样式
        final AlertDialog.Builder mSetPsdDialog= new AlertDialog.Builder(this);
        //实例化自己的alertdialog
        final AlertDialog mAlertDialog = mSetPsdDialog.create();
        //xml——》view 不需要挂载，所以填写null
        final View view = View.inflate(this, R.layout.dialog_set_password, null);
       //得到的view放置到mSetPsdDialog
        mAlertDialog.setView(view);

        //展示相关的mSetPsdDialog
        mAlertDialog.show();
        //拿到相关的组件
        Button  btn_submit = (Button) view.findViewById(R.id.btn_submit);
        Button  btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
       btn_submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //拿到控件
               EditText  confirm_psd= (EditText) view.findViewById(R.id.et_confirm_psd);
               EditText et_psd = (EditText) view.findViewById(R.id.et_psd);
             //得到控件的字符串，密码
               String psdText = confirm_psd.getText().toString().trim();
               String confirm_psdText = et_psd.getText().toString().trim();

               //如果两个et拿到的数值不为空的话
               if (!TextUtils.isEmpty(psdText)&&!TextUtils.isEmpty(confirm_psdText)) {
                   //两个数值相等
                   if (psdText.equals(confirm_psdText)) {
                       //跳装到设置界面
                       Intent intent = new Intent(HomeActivity.this, SetupActivity.class);
                       startActivity(intent);
                       mAlertDialog.dismiss();

                    //存储密码密码到配置文件
                       SpUtils.putString(getApplicationContext(),ConstantValue.MOBILE_SAFE_PSD,psdText);
                   }else {
                       //否则说明密码不相同
                       ToastUtils.showShort(HomeActivity.this,"两次输入的密码必须相同");
                   }
               }else {
                   //密码为空
                   ToastUtils.showShort(HomeActivity.this,"输入密码不能为空");
               }

           }
       });

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
        rv_function = (RecyclerView) findViewById(R.id.rv_function);//因为无法实现点击事件而搁浅了


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        manager.finishAllActivity();
    }
}
