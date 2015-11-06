package utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.jiuzhou.chat.MyApplication;

/**
 * coder by 背离记 on 2015/11/3.
 */
public class UIHelper {

    public static void ToastUtil(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void ToastUtil(String msg){
        Toast.makeText(MyApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void SnackUtil(View view,String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
