//package site.yanhui.thenews.utils;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//
///**
// * Created by Archer on 2017/9/16.
// * <p>
// * 功能描述：封装一个发起网络请求的一个工具类，
// */
//
//public class HttpUtils {
//    public static  void sendOkHttpRequest(String address,okhttp3.Callback callback){
//        OkHttpClient client= new OkHttpClient();
//        Request request= new Request.Builder().url(address).build();
//        client.newCall(request).enqueue(callback);
//    }
//
//}
