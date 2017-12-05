package com.guanhuan.steins.ui.base;

import android.view.View;

/**
 * <页面基础公共功能抽象>
 *
 */
public interface PresentationLayerFunc {
    /**
     * 弹出消息
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 网络请求加载框
     */
    void showProgressDialog();

    /**
     * 隐藏网络请求加载框
     */
    void hideProgressDialog();

    /**
     * 显示软键盘
     *
     * @param focusView
     */
    void showSoftKeyboard(View focusView);

    /**
     * 隐藏软键盘
     */
    void hideSoftKeyboard();
}
