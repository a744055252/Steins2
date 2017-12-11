package com.guanhuan.steins.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guanhuan.steins.R;
import com.guanhuan.steins.bean.entity.ACMsg;
import com.guanhuan.steins.ui.base.CardActivity;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/11/29.
 */

public class BananaAdapter extends RecyclerView.Adapter<BananaAdapter.ViewHolder> {

    private static final String TAG = "BananaAdapter";

    private Context mContext;

    private List<ACMsg> banana;

    public BananaAdapter(List<ACMsg> list){
        this.banana = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_view_main,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ACMsg acmsg = banana.get(position);
                Intent intent = new Intent(mContext, CardActivity.class);
                intent.putExtra(CardActivity.CARD_NAME, acmsg.title);
                intent.putExtra(CardActivity.CARD_IMAGE_ID, acmsg.imageUrl);
                intent.putExtra(CardActivity.CARD_CONTEXT,acmsg.acUrl);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ACMsg acmsg = banana.get(position);
        holder.textView.setText(acmsg.title);
        Glide.with(mContext).load(acmsg.imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(banana == null)
            return 0;
        return banana.size();
    }

    public void setBanana(List<ACMsg> banana) {
        this.banana = banana;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.card_image);
            textView = (TextView) itemView.findViewById(R.id.card_name);
            textView.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        }
    }
}
