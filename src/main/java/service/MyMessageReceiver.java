package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cn.bmob.im.inteface.EventListener;
import cn.bmob.im.util.BmobLog;

/**
 * coder by 背离记 on 2015/11/3.
 */
public class MyMessageReceiver extends BroadcastReceiver{
    // 事件监听
    public static ArrayList<EventListener> ehList = new ArrayList<EventListener>();

    @Override
    public void onReceive(Context context, Intent intent) {
        String json = intent.getStringExtra("msg");
        BmobLog.i("收到的message = " + json);
        //省略其他代码
    }
}
