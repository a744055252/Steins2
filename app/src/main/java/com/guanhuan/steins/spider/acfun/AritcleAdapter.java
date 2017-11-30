package com.guanhuan.steins.spider.acfun;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guanhuan.steins.R;
import com.guanhuan.steins.data.entity.ACMsg;

import java.util.List;

/**
 * Created by guanhuan_li on 2017/11/29.
 */

public class AritcleAdapter extends RecyclerView.Adapter<AritcleAdapter.ViewHolder> {

    private Context mContext;

    private static final String TAG = "AritcleAdapter";

    private List<ACMsg> banana;

    public AritcleAdapter(List<ACMsg> list){
        this.banana = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_view_main,parent,false);

        final ViewHolder holder = new ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                ACMsg acmsg = banana.get(position);
//                Intent intent = new Intent(mContext, FruitActivity.class);
//                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
//                mContext.startActivity(intent);
//            }
//        });
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
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.card_image);
            textView = (TextView) itemView.findViewById(R.id.card_name);
        }
    }
}
