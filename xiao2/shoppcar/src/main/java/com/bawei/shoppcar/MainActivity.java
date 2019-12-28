package com.bawei.shoppcar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.editxinxi)
    EditText editxinxi;
    @BindView(R.id.btn_erweima)
    Button btnErweima;
    @BindView(R.id.btm_canera)
    Button btmCanera;
    @BindView(R.id.btm_xiangce)
    Button btmXiangce;
    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CodeUtils.init(this);

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(image, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });

    }



    @OnClick({R.id.image,R.id.btn_erweima, R.id.btm_canera, R.id.btm_xiangce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_erweima:

                String s = editxinxi.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入信息", Toast.LENGTH_LONG).show();
                } else {
                    Bitmap image1 = CodeUtils.createImage(s, 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.aa1));
                    image.setImageBitmap(image1);
                }

                break;
            case R.id.btm_canera:
                CodeUtils.analyzeByCamera(this);
                break;

            case R.id.btm_xiangce:
                CodeUtils.analyzeByPhotos(this);
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
            }
        });
    }



}
