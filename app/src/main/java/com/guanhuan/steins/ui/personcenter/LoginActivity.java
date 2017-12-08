package com.guanhuan.steins.ui.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guanhuan.steins.R;
import com.guanhuan.steins.biz.personcenter.IUserLoginView;
import com.guanhuan.steins.biz.personcenter.LoginPresenter;
import com.guanhuan.steins.constant.Event;
import com.guanhuan.steins.ui.HomeActivity;
import com.guanhuan.steins.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseActivity implements IUserLoginView {

    /**
     * 用户名
     */
    private EditText userName;

    /**
     * 用户密码
     */
    private EditText password;

    /**
     * 登录
     */
    private Button login;

    private LoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_main);
        super.onCreate(savedInstanceState);

        presenter = mUserLoginPresenter = new LoginPresenter();
        mUserLoginPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        userName = (EditText) findViewById(R.id.login_account);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login_submit);
    }

    @Override
    public void initListeners() {
        login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setHeader() {
        super.setHeader();
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
        switch (event){
            case IMAGE_LOADER_SUCCESS:
                clearEditContent();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_submit:
                mUserLoginPresenter.login(userName.getText().toString(), password.getText().toString());
                break;
        }
        super.onClick(v);
    }

    @Override
    public void clearEditContent() {
        userName.setText("");
        password.setText("");
    }

    @Override
    public void onError(String errorMsg, String code) {
        showToast(errorMsg);
    }

    @Override
    public void onSuccess() {
//        startActivity(HomeActivity.class,null);
        EventBus.getDefault().post(Event.USER_TOKEN_SUCCESS);
        startActivity(HomeActivity.class, null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
