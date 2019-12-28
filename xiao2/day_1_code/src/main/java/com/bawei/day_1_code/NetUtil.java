package com.bawei.day_1_code;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 *@auther:邓先超
 *@Date: 2019/12/27
 *@Time:19:56
 *@Description:
 **/
public class NetUtil {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private final Handler handler;


    public NetUtil(){
        handler = new Handler();
        interceptor=new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();



    }

    public static class NetHttp{
        public static final NetUtil util=new NetUtil();
    }

    public static NetUtil getInstance(){
        return NetHttp.util;
    }



    public void toGits(String url,CallBack callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.error(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.error(new Exception("错误"));
                        }
                    });
                }
            }
        });
    }



    public interface CallBack{
        void success(String succes);
        void error(Exception error);
    }
}
