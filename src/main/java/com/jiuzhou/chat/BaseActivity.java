package com.jiuzhou.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lidroid.xutils.ViewUtils;

import cn.bmob.im.BmobUserManager;

/**
 * coder by 背离记 on 2015/11/3.
 */
public abstract class BaseActivity extends AppCompatActivity{
    public static  String TAG="";
    public BmobUserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        MyApplication.getInstance().addActivity(this);
        if(getLayoutId()!=0){
            setContentView(getLayoutId());
        }
        ViewUtils.inject(this);
        userManager = BmobUserManager.getInstance(this);
        initView();
        initAction();
        TAG=this.getClass().getName();
    }

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initAction();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().finishActivity(this);
    }
}
