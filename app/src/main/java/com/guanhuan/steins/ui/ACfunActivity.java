package com.guanhuan.steins.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guanhuan.steins.R;
import com.guanhuan.steins.spider.acfun.AritcleLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ACfunActivity extends Activity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.acfun_item)
    TabLayout acfunItem;

    AritcleLoader aritcleLoader;
    @BindView(R.id.acfun_content)
    LinearLayout acfunContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acfun_content);
        ButterKnife.bind(this);

        SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) LayoutInflater.
                from(ACfunActivity.this).inflate(R.layout.content_main, null)
                .findViewById(R.id.swipe_refresh);

        ViewGroup parent = (ViewGroup) acfunContent.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }

        swipeRefresh.addView(acfunContent);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        aritcleLoader = new AritcleLoader(recyclerView);
        aritcleLoader.loadAritcle();
    }
}
