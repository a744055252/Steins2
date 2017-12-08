package com.guanhuan.steins.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guanhuan.steins.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public class TitleFragmentAdapter extends FragmentPagerAdapter {

    private String[] title;

    private List<BaseFragment> fragmentList;

    public TitleFragmentAdapter(FragmentManager fm, String[] title, List<BaseFragment> fragmentList) {
        super(fm);
        this.title = title;
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
