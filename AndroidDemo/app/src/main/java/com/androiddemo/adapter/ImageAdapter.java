package com.androiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androiddemo.R;
import com.androiddemo.model.Resim;
import com.androiddemo.com.androiddemo.listener.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private ArrayList<Resim> mList;
    private Context mContext;
    private ItemClickListener mListener;

    public ImageAdapter(Context context, ArrayList<Resim> list, ItemClickListener listener) {
        this.mList = list;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_image,viewGroup,false);
        return new ImageViewHolder(view,i);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Resim resim = mList.get(i);

        Picasso.with(mContext)
                .load(resim.getUrl())
                .into(imageViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;

        public ImageViewHolder(final View itemView, int itemViewType) {
            super(itemView);
            if (itemView != null){
                img = itemView.findViewById(R.id.imageView);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onDetail(itemView,getAdapterPosition());
                    }
                });
            }
        }
    }

}
