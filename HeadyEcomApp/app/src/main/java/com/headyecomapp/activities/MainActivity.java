package com.headyecomapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.headyecomapp.R;
import com.headyecomapp.adapter.CategoryAdapter;
import com.headyecomapp.constants.RequestType;
import com.headyecomapp.controllers.CategoryDataController;
import com.headyecomapp.interfaces.IDataResponseListener;
import com.headyecomapp.model.CategoryDataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDataResponseListener {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryDataAdapter;
    private List<CategoryDataModel> mCategoryDataModel;
    protected static final Gson mGson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        init();

    }

    private void init() {
        mCategoryDataModel = new ArrayList<>();
        mCategoryDataAdapter = new CategoryAdapter(mCategoryDataModel);
        mRecyclerView = findViewById(R.id.rv_category);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCategoryDataAdapter);
        mCategoryDataAdapter.notifyDataSetChanged();
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
        makeCategoryData();
    }

    private void makeCategoryData() {
        CategoryDataController categoryDataController = new CategoryDataController(this);
        categoryDataController.getCategory(this);
    }


    @Override
    public void onStringDataReceived(String dataResponse, RequestType requestType) {

    }

    @Override
    public void onJsonDataReceived(JSONObject responseJsonObject, RequestType requestType) {
        List<CategoryDataModel> dataList;
        try {
            dataList = Arrays.asList(mGson.fromJson((responseJsonObject.
                            get("categories")).toString()
                    , CategoryDataModel[].class));

        } catch (JSONException e) {
            e.printStackTrace();
            dataList = null;
        }

        if (null != dataList) {
            mCategoryDataAdapter.setDataList(dataList);
            mCategoryDataAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onExceptionOccurred(Exception exception, RequestType requestType) {

    }
}
