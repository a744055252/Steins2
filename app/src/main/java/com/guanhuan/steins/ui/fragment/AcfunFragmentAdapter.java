package com.guanhuan.steins.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guanhuan.steins.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public class AcfunFragmentAdapter extends FragmentPagerAdapter {

    private String[] title = {"香蕉榜", "文章区"};

    private List<BaseFragment> fragmentList;

    public AcfunFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
