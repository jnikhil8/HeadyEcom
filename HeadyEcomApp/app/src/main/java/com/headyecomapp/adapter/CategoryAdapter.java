package com.headyecomapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.headyecomapp.R;
import com.headyecomapp.model.CategoryDataModel;
import com.headyecomapp.model.Product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<CategoryDataModel> mCategoryList;
    private List<CategoryDataModel> mOriginalCategoryList;
    private List<Product> mItemDataList;
    private Context mContext;
    private View.OnClickListener mOnClickListener;


    public CategoryAdapter(List<CategoryDataModel> categoryList) {
        this.mCategoryList = categoryList;
        mOriginalCategoryList = categoryList;

    }

    public void setDataList(List<CategoryDataModel> list) {
        mCategoryList = list;
        mOriginalCategoryList = mCategoryList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_adapter_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CategoryDataModel model = mCategoryList.get(position);
        holder.mContentView.setTag(model);
        holder.categoryName.setText(model.getName().trim());

        mItemDataList = model.getProducts();
        CategoryItemAdapter mCategoryItemAdapter = new CategoryItemAdapter(mItemDataList);
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(mCategoryItemAdapter);
        mCategoryItemAdapter.notifyDataSetChanged();
        holder.mRecyclerView.setNestedScrollingEnabled(false);
        holder.mRecyclerView.setHasFixedSize(false);

        if (mOnClickListener != null) {
            holder.mHeaderLayout.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        View mContentView;
        RecyclerView mRecyclerView;
        RelativeLayout mHeaderLayout;

        public MyViewHolder(View view) {
            super(view);
            mContentView = view;
            mRecyclerView = view.findViewById(R.id.rv_category_item_list);
            categoryName = view.findViewById(R.id.tv_heading);
        }
    }
}