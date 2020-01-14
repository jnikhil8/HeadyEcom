package com.headyecomapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.headyecomapp.R;
import com.headyecomapp.adapter.VariantAdapter;
import com.headyecomapp.constants.AppConstants;
import com.headyecomapp.model.Product;
import com.headyecomapp.model.Variant;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    protected static final Gson mGson = new GsonBuilder().create();
    private TextView mTvProductName;
    private Product mProduct;
    private VariantAdapter mVariantAdapter;
    private List<Variant> mVariantList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_layout);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mProduct = (Product) bundle.getSerializable(AppConstants.PRODUCT_DATA);

        }
        initView();


    }

    private void initView() {
        mTvProductName = findViewById(R.id.tv_product_name);
        mTvProductName.setText(mProduct.getName());
        mVariantList = mProduct.getVariants();
        if (mVariantList!=null)
        {
            initRecyclerView();
        }

    }

    private void initRecyclerView() {
        mVariantAdapter = new VariantAdapter(mVariantList);
        mRecyclerView = findViewById(R.id.rv_color);
        mRecyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mVariantAdapter);
        mVariantAdapter.notifyDataSetChanged();
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
    }


}
