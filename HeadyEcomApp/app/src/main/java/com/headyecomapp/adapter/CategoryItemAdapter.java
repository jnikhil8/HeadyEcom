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
import com.headyecomapp.activities.ProductDetailActivity;
import com.headyecomapp.constants.AppConstants;
import com.headyecomapp.model.Product;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.MyViewHolder> {

    private List<Product> mCategoryList;
    private Context mContext;


    CategoryItemAdapter(List<Product> celebrityData
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
        final Product model = mCategoryList.get(position);
        holder.mContentView.setTag(model);
        if (!TextUtils.isEmpty(model.getName())) {
            holder.productName.setText(model.getName().trim());
        }
        if (!TextUtils.isEmpty(model.getDateAdded())) {
            holder.date.setText(model.getDateAdded().trim());
        }
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent;
                intent = new Intent(mContext, ProductDetailActivity.class);
                bundle.putSerializable(AppConstants.PRODUCT_DATA, model);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView productName;
        View mContentView;
        ImageView mIvCelebrityImage;

        MyViewHolder(View view) {
            super(view);
            mContentView = view;
            productName = view.findViewById(R.id.tv_product_name);
            date = view.findViewById(R.id.tv_date);

        }
    }
}