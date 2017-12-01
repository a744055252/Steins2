package com.guanhuan.steins.ui.base;

import android.content.Context;
import android.view.View;

import com.guanhuan.steins.util.Toasts;

/**
 * <页面基础公共功能实现>
 *
 */
public class PresentationLayerFuncHelper implements PresentationLayerFunc {

    private Context context;

    public PresentationLayerFuncHelper(Context context) {
        this.context = context;
    }

    @Override
    public void showToast(String msg) {
        Toasts.showShort(msg);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showSoftKeyboard(View focusView) {

    }

    @Override
    public void hideSoftKeyboard() {

    }
}
