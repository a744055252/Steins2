package com.guanhuan.steins.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guanhuan.steins.App;
import com.guanhuan.steins.R;
import com.guanhuan.steins.config.Constants;
import com.guanhuan.steins.data.entity.Fruit;
import com.guanhuan.steins.spider.acfun.AritcleAdapter;
import com.guanhuan.steins.spider.acfun.AritcleLoader;
import com.guanhuan.steins.util.PreferencesLoader;
import com.guanhuan.steins.util.Toasts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    ImageView user_image;
    TextView user_name;
    TextView user_email;
    View headerLayout;

    PreferencesLoader loader;

    public static final int UPDATE_USER = 1;

    private static final String TAG = "MainActivity";

    //用于刷新的广播
    private BroadcastReceiver refresh = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onResume();
        }
    };
    private IntentFilter intentFilter;

    public MainActivity() {
        loader = new PreferencesLoader(App.getsContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        iniNav_header();

        //刷新
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Toast.makeText(App.getsContext(), "刷新", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });

        //设置刷新广播
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_REFRESH_USER);
        registerReceiver(refresh, intentFilter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ACfunActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                //登出
                logout();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 初始化侧边栏头
     *
     * @param
     * @Date: 15:01 2017/11/24
     */
    private void iniNav_header() {

        user_image = (ImageView) headerLayout.findViewById(R.id.user_image);
        user_name = (TextView) headerLayout.findViewById(R.id.user_name);
        user_email = (TextView) headerLayout.findViewById(R.id.user_email);
        String token = loader.getString(Constants.AUTHORIZATION);
        Log.i(TAG, "iniNav_header: " + token);
        if (token == null || token.equals("")) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            };
            user_image.setOnClickListener(listener);
            user_name.setOnClickListener(listener);
        }

    }

    public void loadUser() {

        //如果已经登陆则加载用户信息
        String userName = loader.getString(Constants.LOGIN_USERNAME);
        String userEmail = loader.getString(Constants.LOGIN_EMAIL);
        Log.i(TAG, "loadUser:" + userName + "_" + userEmail);
        if (userName != null && !userName.equals("")) {
            user_name.setText(userName);
            user_email.setText(userEmail);
        }
    }

    public void logout() {
        new AlertDialog.Builder(MainActivity.this).setTitle("系统提示")
                .setMessage("是否退出登陆")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loader.saveString(Constants.AUTHORIZATION, "");
                        loader.saveString(Constants.LOGIN_USERNAME, "");
                        loader.saveString(Constants.LOGIN_EMAIL, "");
                        loader.saveString(Constants.LOGIN_IMAGE, "");
                        user_name.setText("请登陆");
                        user_email.setText("");
                        Toasts.showShort("退出成功");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUser();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(refresh);
    }
}
