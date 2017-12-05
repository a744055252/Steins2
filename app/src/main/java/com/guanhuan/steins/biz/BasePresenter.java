package com.guanhuan.steins.biz;

import com.guanhuan.steins.http.ObjectLoader;

/**
 * <基础业务类>
 *
 */
public abstract class BasePresenter<V extends IMvpView> extends ObjectLoader implements Presenter<V> {
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

    }
}
