package com.guanhuan.steins.biz;

/**
 * <功能详细描述>
 *
 */
public interface IMvpView {
    void onError(String errorMsg, String code);

    void onSuccess();

    void showLoading();

    void hideLoading();
}
