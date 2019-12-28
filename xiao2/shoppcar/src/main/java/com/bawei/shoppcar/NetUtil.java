package com.bawei.shoppcar;

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
 *@Time:19:00
 *@Description:
 **/
public class NetUtil {
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor interceptor;
    private final Handler handler;

    public NetUtil (){
        interceptor=new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        //1构造一个ok对象
        handler = new Handler();
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();

    }

    public static class NetHttp{
        public static final NetUtil util=new NetUtil();
    }


    public static NetUtil getInstance(){
        return NetHttp.util;
    }

    public void getGits(String url,CallBack callBack){



        //2构造一个request请求对象
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();


        //3根据ok和request对象,构造一个可执行的的call对象
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.error(e);
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
                            callBack.success(string);
                        }
                    });

                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.error(new Exception("json失败"));
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
