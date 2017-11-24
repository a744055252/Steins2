package com.guanhuan.steins.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.guanhuan.steins.R;
import com.guanhuan.steins.login.LoginLoader;
import com.guanhuan.steins.login.UserLoader;
import com.guanhuan.steins.util.Toasts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_submit)
    Button loginSubmit;
    @BindView(R.id.login_register)
    Button loginRegister;

    LoginLoader loginLoader = new LoginLoader();
    UserLoader userLoader = new UserLoader();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_submit)
    public void loging(){
        String account = loginAccount.getText().toString();
        String password = loginPassword.getText().toString();

        if(account == null || account.equals("")) {
            Toasts.showShort("请输入账号");
            return;
        }
        if(password == null || password.equals("")) {
            Toasts.showShort("请输入密码");
            return;
        }

        loginLoader.login(account, password);

        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

