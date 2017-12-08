package com.guanhuan.steins.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guanhuan.steins.R;
import com.guanhuan.steins.ui.base.BaseActivity;
import com.guanhuan.steins.ui.base.BaseFragment;
import com.guanhuan.steins.ui.adapter.TitleFragmentAdapter;
import com.guanhuan.steins.util.ToastUtil;
import com.guanhuan.steins.util.Toasts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public class AcfunFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    private View view;
    private BaseFragment currentFragment;
    private ViewPager viewpager;
    private TabLayout tabLayout;

    private List<BaseFragment> fragmentList;
    private String[] title = {"香蕉榜", "文章区"};

    TitleFragmentAdapter adapter;

    private BaseActivity context;

    private static final String TAG = "AcfunFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.acfun_fragment, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i(TAG, "onCreateView: running!");
        return view;
    }



    @Override
    public void initViews() {
        super.initViews();
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.acfun_items);
    }

    @Override
    public void initListeners() {
        super.initListeners();
        viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void setHeader() {
        super.setHeader();
    }

    @Override
    public void initData() {
        super.initData();
        fragmentList = new ArrayList<>(2);
        fragmentList.add(new BananaFragment());
        fragmentList.add(new ArticleFragment());
        adapter = new TitleFragmentAdapter(getChildFragmentManager(), title, fragmentList);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (BaseActivity)context;
    }


//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.acfun_fragment_content, fragment);
//        transaction.commit();
//        currentFragment = (BaseFragment)fragment;
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.item_article :
//                replaceFragment(bananaFragment);
//                break;
//            case R.id.item_bananar :
//                replaceFragment(articleFragment);
//                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        TabLayout main_tab = (TabLayout) context.findViewById(R.id.main_items);
//        main_tab.
        Log.i(TAG, "onPageScrolled: "+position+"_"+positionOffset+"_"+positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
