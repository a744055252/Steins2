package com.guanhuan.steins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.login.LoginLoader;
import com.guanhuan.steins.login.UserLoader;
import com.guanhuan.steins.util.PreferencesLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn)
    Button btn;

    private LoginLoader loginLoader;
    private UserLoader userLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PreferencesLoader loader = new PreferencesLoader(App.getsContext());
        String token = loader.getString(Constants.AUTHORIZATION);
        Log.i(TAG, "___Token:" + token);

        Log.i(TAG, "btn : " + btn);


//        UserLoader userLoader = new UserLoader();
//        userLoader.getLoginUser();


//        List<User> userList = App.getsDb().query(new QueryBuilder(User.class)
//                .where("account = ?" , new String[]{"a744055252"})
//                .limit(0, 1)
//        );
//
//        if(userList != null && !userList.isEmpty()){
//            User user = userList.get(0);
//            Log.i(TAG, "_____user:+" + user.toString());
//        } else {
//            Log.i(TAG, "_____userList is empty");
//        }

    }

    @OnClick(R.id.btn)
    public void login(){
        Log.i(TAG, "btn: click!");
        UserLoader userLoader = new UserLoader();
        userLoader.getLoginUser();
    }


}
