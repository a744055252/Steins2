package com.guanhuan.steins.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.guanhuan.steins.R;
import com.guanhuan.steins.biz.personcenter.IHomeView;
import com.guanhuan.steins.biz.personcenter.UserPresenter;
import com.guanhuan.steins.bridge.BridgeFactory;
import com.guanhuan.steins.bridge.Bridges;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefManager;
import com.guanhuan.steins.bridge.cache.sharePref.SharedPrefUser;
import com.guanhuan.steins.capabilities.cache.BaseSharedPreference;
import com.guanhuan.steins.constant.Event;
import com.guanhuan.steins.ui.base.BaseActivity;
import com.guanhuan.steins.ui.base.BaseFragment;
import com.guanhuan.steins.ui.adapter.TitleFragmentAdapter;
import com.guanhuan.steins.ui.fragment.AcfunFragment;
import com.guanhuan.steins.ui.fragment.BilibiliFragment;
import com.guanhuan.steins.ui.fragment.GuanchaFragment;
import com.guanhuan.steins.ui.fragment.WumaoFragment;
import com.guanhuan.steins.util.PreferencesLoader;
import com.guanhuan.steins.util.Toasts;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guanhuan
 */

public class HomeActivity extends BaseActivity implements IHomeView,NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar1)
    Toolbar toolbar;
//    @BindView(R.id.tab)
//    TabLayout mTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;
    @BindView(R.id.main_items)
    TabLayout main_items;

    private ImageView user_image;
    private TextView user_name;
    private TextView user_email;
    private View headerLayout;
    private BaseFragment currentFragment;

    private PreferencesLoader loader;
    private List<BaseFragment> fragmentList;
    private TitleFragmentAdapter adapter;
    private String[] titles = {"ACFun", "BiliBili", "五毛网", "观察者"};

    private SharedPrefManager spmanager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
    private BaseSharedPreference preference;

    private UserPresenter userPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        presenter = userPresenter = new UserPresenter();
        userPresenter.attachView(this);
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {
        EventBus.getDefault().post(Event.USER_LOGING_SUCCESS);
//        loadUser();
        Toasts.showShort("登录成功");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        user_image = (ImageView) headerLayout.findViewById(R.id.user_image);
        user_name = (TextView) headerLayout.findViewById(R.id.user_name);
        user_email = (TextView) headerLayout.findViewById(R.id.user_email);
    }

    public void initListeners() {
//        //刷新
//        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//
//            @Override
//            public void onRefresh() {
//                Toasts.showShort("刷新");
//                currentFragment.refresh();
//                swipeRefresh.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void initData() {
//        currentFragment = new AcfunFragment();
//        replaceFragment(currentFragment);
        initTitle();
        preference = spmanager.getSharedPref(SharedPrefManager.SharedPrefs.USER);
        String token = preference.getString(SharedPrefUser.USER_TOKEN, "");
        Log.i(TAG, "iniNav_header: " + token);
        if (token == null || token.equals("")) {
            user_image.setOnClickListener(listener);
            user_name.setOnClickListener(listener);
        } else {
            loadUser();
        }
    }

    private void initTitle(){
        fragmentList = new ArrayList<>(4);
        fragmentList.add(new AcfunFragment());
        fragmentList.add(new BilibiliFragment());
        fragmentList.add(new WumaoFragment());
        fragmentList.add(new GuanchaFragment());
        adapter = new TitleFragmentAdapter(getSupportFragmentManager(), titles, fragmentList);
        main_viewpager.setAdapter(adapter);
        main_items.setupWithViewPager(main_viewpager);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
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

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadUser() {

        //如果已经登陆则加载用户信息
//        String userName = loader.getString(Constants.LOGIN_USERNAME);
//        String userEmail = loader.getString(Constants.LOGIN_EMAIL);
        preference = spmanager.getSharedPref(SharedPrefManager.SharedPrefs.USER);
        String userName = preference.getString(SharedPrefUser.USER_NAME, "");
        String userEmail = preference.getString(SharedPrefUser.USER_EMAIL, "");
        if (!userName.equals("")) {
            user_name.setText(userName);
            user_email.setText(userEmail);
        }
    }

    public void logout() {
        drawer.closeDrawer(GravityCompat.START);
        new AlertDialog.Builder(HomeActivity.this).setTitle("系统提示")
                .setMessage("是否退出登陆")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preference = spmanager.getSharedPref(SharedPrefManager.SharedPrefs.USER);
                        preference.saveString(SharedPrefUser.USER_TOKEN, "");
                        preference.saveString(SharedPrefUser.USER_EMAIL, "");
                        preference.saveString(SharedPrefUser.USER_NAME, "");
                        preference.saveString(SharedPrefUser.USER_IMAGE, "");
                        user_name.setText("请登陆");
                        user_email.setText("");
                        Toasts.showShort("退出成功");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
        user_image.setOnClickListener(listener);
        user_name.setOnClickListener(listener);
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
        switch (event){
            case USER_LOGING_SUCCESS:
                loadUser();
                break;
            case USER_TOKEN_SUCCESS:
                userPresenter.getLoginUser();
                break;
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(HomeActivity.this, com.guanhuan.steins.ui.personcenter.LoginActivity.class);
            startActivity(intent);
        }
    };

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_layout, fragment);
        transaction.commit();
        currentFragment = (BaseFragment)fragment;
    }
}
