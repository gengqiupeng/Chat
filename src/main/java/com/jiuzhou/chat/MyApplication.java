package com.jiuzhou.chat;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

import utils.Config;
import utils.LogUtil;
import cn.bmob.im.BmobChat;

/**
 * coder by 背离记 on 2015/11/3.
 */
public class MyApplication extends Application{
    String TAG="MyApplication";

    private static Stack<Activity> activityStack;
    private static MyApplication singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton=this;
        //可设置调试模式，当为true的时候，会在logcat的BmobChat下输出一些日志，包括推送服务是否正常运行，如果服务端返回错误，也会一并打印出来。方便开发者调试，正式发布应注释此句。
        BmobChat.DEBUG_MODE = true;
        //BmobIM SDK初始化--只需要这一段代码即可完成初始化
        BmobChat.getInstance(this).init(Config.applicationId);
        //省略其他代码
    }

    public static MyApplication getInstance(){
        return singleton;
    }

    /**
     * 把Activity添加到栈中
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<>();
        }
        activityStack.add(activity);
        LogUtil.i(TAG,activityStack.size());
    }

    /**
     * 当前Activity
     * @return
     */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }


    public void finishCurrentActivity(){
        Activity activity= activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }

    public void finishActivity(Class<?> cls){
        for(Activity activity:activityStack){
            if(activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity(){
        for(int i=0,size=activityStack.size();i<size;i++){
            if(null!=activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void ExitApp(){
        try {
            finishAllActivity();
        }catch (Exception e){

        }
    }

}
