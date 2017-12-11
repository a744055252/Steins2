package com.guanhuan.steins.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guanhuan.steins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guanhuan_li on 2017/12/11.
 */

public class CardActivity extends BaseActivity {

    public static final String CARD_NAME = "card_name";
    public static final String CARD_IMAGE_ID = "card_image_id";
    public static final String CARD_CONTEXT = "card_context";

    @BindView(R.id.card_image_view)
    ImageView cardImageView;
    @BindView(R.id.card_content_text)
    TextView cardContentText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.index_card);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
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
        ButterKnife.bind(this);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String cardName = intent.getStringExtra(CARD_NAME);
        String cardImage = intent.getStringExtra(CARD_IMAGE_ID);
        String cardContext = intent.getStringExtra(CARD_CONTEXT);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(cardName);
        Glide.with(this).load(cardImage).into(cardImageView);
        cardContentText.setText(generateFruitContent(cardContext));
    }

    private String generateFruitContent(String fruitName){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 60; i++){
            sb.append(fruitName);
        }
        return sb.toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
