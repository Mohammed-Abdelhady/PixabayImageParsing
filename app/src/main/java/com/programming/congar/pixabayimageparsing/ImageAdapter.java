package com.programming.congar.pixabayimageparsing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Image> mData;

    public ImageAdapter(Context mContext, ArrayList<Image> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.image_row_item , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyViewHolder holder, int i) {
        Image image = mData.get(i);

        String imageUrl = image.getImageUrl();
        String creatorName = image.getCreator();
        int likeCount = image.getLikeCount();

        holder.tvCreator.setText(creatorName);
        holder.tvLikes.setText("Likes " + likeCount);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.ivImage);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCreator , tvLikes;
        private ImageView ivImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCreator = itemView.findViewById(R.id.text_view_creator);
            tvLikes = itemView.findViewById(R.id.text_view_like);
            ivImage = itemView.findViewById(R.id.image_view);
        }
    }
}
