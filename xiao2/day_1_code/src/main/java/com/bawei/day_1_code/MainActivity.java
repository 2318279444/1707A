package com.bawei.day_1_code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetUtil.getInstance().toGits("http://blog.zhaoliang5156.cn/api/student/clazzstudent.json", new NetUtil.CallBack() {
            @Override
            public void success(String succes) {
                Toast.makeText(MainActivity.this,""+succes,Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(Exception error) {
                Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
            }

        });
    }
}
