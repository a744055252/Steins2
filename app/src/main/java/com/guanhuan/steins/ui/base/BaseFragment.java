package com.guanhuan.steins.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanhuan.steins.App;
import com.guanhuan.steins.biz.BasePresenter;
import com.guanhuan.steins.biz.IMvpView;
import com.guanhuan.steins.constant.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public abstract class BaseFragment extends Fragment implements CreateInit, IMvpView, View.OnClickListener {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    public BasePresenter presenter;

    public final String TAG = this.getClass().getSimpleName();

    private BaseActivity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View temp = super.onCreateView(inflater, container, savedInstanceState);
        initViews();
        initListeners();
        initData();
        setHeader();
        EventBus.getDefault().register(this);
        return temp;
    }

    @Override
    public void onClick(View view) {

    }

    @Subscribe
    public void onEventMainThread(Event event) {

    }

    @Override
    public void onResume() {
        App.app.currentActivityName = this.getClass().getName();
        super.onResume();
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setHeader() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (BaseActivity) context;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView(this);
            //清除订阅
            presenter.onStop();
        }
        //已发送的请求未取消
        super.onDestroyView();
    }

    /** 刷新数据 */
    public abstract void refresh();

}
