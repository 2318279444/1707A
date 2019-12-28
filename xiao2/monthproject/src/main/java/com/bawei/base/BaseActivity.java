package com.bawei.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
 *@auther:邓先超
 *@Date: 2019/12/28
 *@Time:9:00
 *@Description:
 **/
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(inilayout());

        iniview();

        inidata();
    }

    protected abstract void inidata();

    protected abstract void iniview();

    protected abstract int inilayout();
}
