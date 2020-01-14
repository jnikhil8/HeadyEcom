package com.headyecomapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.headyecomapp.R;
import com.headyecomapp.constants.AppConstants;
import com.headyecomapp.model.Variant;


import java.util.List;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.MyViewHolder> {

    private List<Variant> mCategoryList;
    private Context mContext;


    public VariantAdapter(List<Variant> celebrityData
    ) {
        this.mCategoryList = celebrityData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_adapter_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Variant model = mCategoryList.get(position);
        holder.mContentView.setTag(model);
        if (model != null) {
//            holder.productColor.setText(String.valueOf(model.getColor()));
            holder.productSize.setText(String.valueOf(model.getSize()));
            holder.productPrice.setText(String.valueOf(model.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productColor;
        TextView productSize;
        TextView productPrice;
        View mContentView;
        MyViewHolder(View view) {
            super(view);
            mContentView = view;
            productColor = view.findViewById(R.id.tv_color);
            productSize = view.findViewById(R.id.tv_size);
            productPrice = view.findViewById(R.id.tv_price);


        }
    }
}