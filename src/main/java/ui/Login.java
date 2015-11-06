package ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuzhou.chat.BaseActivity;
import com.jiuzhou.chat.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import bean.User;
import cn.bmob.v3.listener.SaveListener;
import utils.LogUtil;
import utils.UIHelper;
import view.MyEditText;

/**
 * coder by 背离记 on 2015/11/5.
 */
public class Login extends BaseActivity{
    @ViewInject(R.id.button_login)
    Button button_login;
    @ViewInject(R.id.text_to_signup)
    TextView text_to_signup;
    @ViewInject(R.id.edit_userName)
    MyEditText editName;
    @ViewInject(R.id.edit_userPwd)
    MyEditText editPwd;
    @ViewInject(R.id.root_login)
    RelativeLayout root;

    @Override
    public int getLayoutId() {
        return R.layout.log_in;
    }

    @Override
    public void initView() {
        ActivityOptions.makeSceneTransitionAnimation(this, button_login, "Button");
    }

    @Override
    public void initAction() {

    }

    @OnClick(R.id.text_to_signup)
    public void toSignUp(View v){
        Intent intent = new Intent(Login.this,SignUp.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,button_login,"Button");
        startActivity(intent,options.toBundle());
        finish();
    }

    @OnClick(R.id.button_login)
    public void Login(View v){
        final User user = new User();
        String name=editName.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
        if(name.isEmpty()){
            UIHelper.SnackUtil(root, "账号不能为空");
            return;
        }
        if(pwd.isEmpty()){
            UIHelper.SnackUtil(root, "账号不能为空");
            return;
        }
        user.setUsername(name);
        user.setPassword(pwd);
        user.login(Login.this, new SaveListener() {
            @Override
            public void onSuccess() {
                UIHelper.ToastUtil("登录成功，稍后跳转");
            }

            @Override
            public void onFailure(int i, String s) {
                LogUtil.i(TAG,i+s);
                UIHelper.SnackUtil(root,s);
            }
        });
    }

}
