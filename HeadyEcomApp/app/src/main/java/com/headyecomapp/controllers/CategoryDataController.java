package com.headyecomapp.controllers;

import android.content.Context;

import com.headyecomapp.constants.AppConstants;
import com.headyecomapp.constants.RequestType;
import com.headyecomapp.interfaces.IDataResponseListener;
import com.headyecomapp.utils.DownloadUtils;

import org.json.JSONObject;

public class CategoryDataController {
    private Context mContext;

    public CategoryDataController(Context context) {
        mContext = context;
    }


    public void getCategory( IDataResponseListener responseListener) {
        String getCategoryURL = AppConstants.IP_ADDRESS;


        JSONObject requestObject = new JSONObject();
        DownloadUtils.makeJsonRequestWithGetMethod(mContext, getCategoryURL, requestObject,
                responseListener, RequestType.GET_CATEGORY_DATA);

    }
}
