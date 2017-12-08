package com.guanhuan.steins.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanhuan.steins.R;
import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.biz.IAdapterView;
import com.guanhuan.steins.biz.personcenter.BananaPresenter;
import com.guanhuan.steins.constant.Event;
import com.guanhuan.steins.spider.acfun.AritcleAdapter;
import com.guanhuan.steins.ui.adapter.BananaAdapter;
import com.guanhuan.steins.ui.base.BaseFragment;
import com.guanhuan.steins.util.Toasts;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public class BananaFragment extends SwipeRefreshFragment implements IAdapterView {

    private View view;
    private BananaAdapter adapter;
    private RecyclerView recyclerView;
    private BananaPresenter bananaPresenter;

    private List<ACMsg> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.banana_fragment, container, false);
        super.onCreateView(inflater, container, savedInstanceState);


        return view;
    }

    @Override
    public void initViews() {
        super.initViews();
        recyclerView = (RecyclerView) view.findViewById(R.id.banana_recyclerView);
        super.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);

    }

    @Override
    public void initData() {
        super.initData();
        presenter = bananaPresenter = new BananaPresenter();
        presenter.attachView(this);
        bananaPresenter.getBanana();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        swipeRefreshLayout.setRefreshing(true);
        bananaPresenter.getBanana();
        swipeRefreshLayout.setRefreshing(false);
        Toasts.showShort("刷新成功");
    }

    @Override
    public void setData(List<?> list) {
        Log.i(TAG, "setData: ");
        adapter = new BananaAdapter((List<ACMsg>) list);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

}
