package site.yanhui.mobilesafe;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import site.yanhui.mobilesafe.activity.HomeActivity;
import site.yanhui.mobilesafe.gson.Update;
import site.yanhui.mobilesafe.utils.AppManagerUtils;
import site.yanhui.mobilesafe.utils.HttpUtils;
import site.yanhui.mobilesafe.utils.ToastUtils;

@RuntimePermissions
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private static final int UPDATE_VERSION = 100;
    private static final int ENTER_HOME = 101;
    private TextView tv_versionName;
    private int mVersionCode;



    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    showUpdateDialog();
                    break;
                case  ENTER_HOME:
                    enterHome();
            }
        }

    };
    private String des;
    private String updateDownloadUrl;

    /**
     * 进入Home界面
     */
    private void enterHome() {

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();//结束当前app
    }

    /**
     * 弹出对话框更新界面
     */
    private void showUpdateDialog() {
        //弹出对话框，是依赖于activity存在的
        final AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setIcon(R.drawable.ic_launcher); //设置左上角图标
        alertDialog.setTitle("版本更新");
        alertDialog.setMessage(des);
        alertDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  SplashActivityPermissionsDispatcher.downNewApkWithCheck(SplashActivity.this);
            }
        });

        alertDialog.setNegativeButton("取消更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();

            }
        });
        alertDialog.setCancelable(false);//不能被取消
        alertDialog.show();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppManagerUtils manager = AppManagerUtils.getAppManager();
        manager.addActivity(this);

        /*
         1.隐藏actionBar的第一种方式
         2.第二种方式直接在Manifest文件中设置theme主题隐藏

         区别：1.在oncreate里面隐藏的话，下一个Activity里，同样存在
               2.在manifest中隐藏的话，所有的anctivity中都不可见，因为是在根节点隐藏
         */
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.hide();
//        }

        initUI();
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        //1.应用名称
        String versionName = getVersionName();
        //2.给textView赋值
        tv_versionName.setText(versionName);
        //3.检测本地版本号和服务器版本号是否相同,不相同说明有新版本，后台更新
        mVersionCode = getVersionCode();
//        4.获得服务端的版本号
//        http://ogtmd8elu.bkt.clouddn.com/update_mobilesafe.json
//        包含更新版本的名称
//        新版本的描述信息
//        获得版本号
//        下载地址
//         "versionName":"2.0",
//        "versionCode":"2"
//        "versionDes":"2.0版本发布了，狂拽酷炫吊炸天"
//        "downloadUrl":"http://yanhui.site"（替换成下载地址）
         checkVersion();
    }

    /**
     * 检测版本号
     *
     */
    private void checkVersion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final long startTime = System.currentTimeMillis();
                final Message message=Message.obtain();
                String address="http://ogtmd8elu.bkt.clouddn.com/MobilesafeUpdate.json";
                HttpUtils.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showShort(SplashActivity.this,"更新数据失败，请检查网络");
                            }
                        });

                        message.what=ENTER_HOME;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonResponseText = response.body().string();
                            Log.d(TAG, "onResponse: " + jsonResponseText);
                            int versionCloudCode = parseJsonObject(jsonResponseText);//解析json
                            if (mVersionCode < versionCloudCode) {
                                message.what = UPDATE_VERSION;
                            } else {
                                message.what = ENTER_HOME;
                            }
                            long endtime = System.currentTimeMillis();
                            if (endtime - startTime < 4000) {
                                try {
                                    Thread.sleep(4000 - (endtime - startTime));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            mHandler.sendMessage(message);
                        }

                });
            }
        }).start();

    }

    /**
     * 解析拿到的jsonObject对象
     * @param jsonData    传入json返回的String
     */
    private int parseJsonObject(String jsonData) {
        Gson gson= new Gson();
        Update update=gson.fromJson(jsonData,Update.class);
        Log.d(TAG, "parseJsonObject: "+update.getVersionDes());
        Log.d(TAG, "parseJsonObject: "+update.getVersionName());
        Log.d(TAG, "parseJsonObject: "+update.getVersionCode());
        Log.d(TAG, "parseJsonObject: "+update.getDownloadUrl());
        des = update.getVersionDes();
        updateDownloadUrl = update.getDownloadUrl();

        return Integer.parseInt(update.getVersionCode());

    }

    /**
     * 获取版本号
     *
     * @return 应用版本名称 返回0 代表有异常
     */
    private int getVersionCode() {
        //1.包管理者对象PackageManager
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(this.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名称
     *
     * @return 应用版本名称 返回null 代表有异常
     */
    private String getVersionName() {
        //1.包管理者对象PackageManager
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(this.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        tv_versionName = (TextView) findViewById(R.id.version_name);
    }

    /*
     *
     * 使用xutils3 下载模拟的最新的apk文件
     *
     */
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void downNewApk() {
      ToastUtils.showShort(SplashActivity.this,"正在下载.....");
        //1.判断sd卡是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
             //获得sd路径
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "mobilesafe1.apk";//指定存储路径和存储的名称
            String url =updateDownloadUrl;
            RequestParams params = new RequestParams(url);
            params.setSaveFilePath(path);
//            params.setAutoRename(true);//自动把名字改成服务器上的app名称，会覆盖，但是一旦重复下载就会多出来。
            x.http().post(params, new org.xutils.common.Callback.CommonCallback<File>() {
                @Override
                public void onSuccess(File result) {
                    Log.i(TAG, "onSuccess: ");
                    //获得的result就是下载获得的文件
                    //自动安装
                    installApk(result);
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }
                @Override
                public void onCancelled(CancelledException cex) {

                }
                @Override
                public void onFinished() {

                }
            });
        }
    }

    /**
     * @param file 待安装的文件
     */
    private void installApk(File file ) {
     //系统应用界面，看源码，分析，安装apk入口
        Intent intent= new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.fromFile(file));//得到数据源
//        intent.setType("application/vnd.android.package-archive");

        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0 :
                enterHome();
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 给个理由啊
     * @param request 继续发送申请
     */
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void ShowRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("需要使用SD卡权限，自动下载最新版本的APK")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed(); //发送申请
                    }
                })
                .create()
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void Denied() {
        Toast.makeText(this, "未授权", Toast.LENGTH_SHORT).show();
        enterHome();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void NeverAsk() {
        Toast.makeText(this, "不再询问", Toast.LENGTH_SHORT).show();
        enterHome();
    }
}



