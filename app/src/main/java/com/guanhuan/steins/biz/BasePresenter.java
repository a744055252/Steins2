package com.guanhuan.steins.biz;

import com.guanhuan.steins.http.ObjectLoader;

import rx.subscriptions.CompositeSubscription;

/**
 * <基础业务类>
 * 调用CompositeSubscription.add()将 Subscription 加入管理避免内存泄漏
 */
public abstract class BasePresenter<V extends IMvpView> extends ObjectLoader implements Presenter<V> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();;

    protected V mvpView;

    public void attachView(V view) {
        mvpView = view;
    }

    public void detachView(V view) {
        mvpView = null;
    }

    public String getName() {
        return mvpView.getClass().getSimpleName();
    }

    public void onStop() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }
}
