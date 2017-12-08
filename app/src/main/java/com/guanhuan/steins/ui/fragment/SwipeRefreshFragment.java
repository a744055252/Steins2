package com.guanhuan.steins.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanhuan.steins.ui.base.BaseFragment;

/**
 * Created by guanhuan_li on 2017/12/8.
 */

public class SwipeRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    protected SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void initListeners() {
        super.initListeners();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }
}
