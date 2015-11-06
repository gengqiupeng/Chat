package ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jiuzhou.chat.BaseActivity;
import com.jiuzhou.chat.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import bean.User;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;
import utils.LogUtil;
import utils.UIHelper;
import view.MyEditText;

/**
 * coder by 背离记 on 2015/11/3.
 */
public class SignUp extends BaseActivity {
    @ViewInject(R.id.button_signup)
    Button button_signUp;
    @ViewInject(R.id.edit_userName)
    MyEditText editName;
    @ViewInject(R.id.edit_userPwd)
    MyEditText editPwd;
    @ViewInject(R.id.root_sign)
    RelativeLayout root;

    @Override
    public int getLayoutId() {
        return R.layout.sign_up;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initAction() {
    }

    @OnClick(R.id.button_signup)
    public void signup(View v){
        final User user = new User();
        String name=editName.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
        if(name.isEmpty()){
            UIHelper.SnackUtil(root,"账号不能为空");
            return;
        }
        if(pwd.isEmpty()){
            UIHelper.SnackUtil(root, "账号不能为空");
            return;
        }
        user.setUsername(name);
        user.setPassword(pwd);
        user.setDeviceType("android");
        user.setTest("test");
        user.setInstallId(BmobInstallation.getInstallationId(this));
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                userManager.bindInstallationForRegister(user.getUsername());
                LogUtil.i(TAG, "Success");
                UIHelper.ToastUtil("注册成功");
            }
            @Override
            public void onFailure(int i, String s) {
                //TODO 还是用Toast比较好啊，Snake也可以
                LogUtil.i(TAG, "code:" + i + "String:" + s);
            }
        });
    }

    @OnClick(R.id.text_to_login)
    public void toLogin(View v){
        Intent intent = new Intent(SignUp.this,Login.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,button_signUp,"Button");
        startActivity(intent,options.toBundle());
        finish();
    }

}
