package com.guanhuan.steins.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanhuan.steins.R;
import com.guanhuan.steins.ui.base.BaseFragment;
import com.guanhuan.steins.util.Toasts;

/**
 * Created by guanhuan_li on 2017/12/7.
 */

public class WumaoFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.wumao_fragment, container, false);
        return view;
    }

}
