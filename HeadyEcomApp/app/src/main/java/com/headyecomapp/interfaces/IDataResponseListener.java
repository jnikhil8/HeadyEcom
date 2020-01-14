package com.headyecomapp.interfaces;

import com.headyecomapp.constants.RequestType;

import org.json.JSONObject;

/**
 */
public interface IDataResponseListener {

    void onStringDataReceived(String dataResponse, RequestType requestType);

    void onJsonDataReceived(JSONObject responseJsonObject, RequestType requestType);

    void onExceptionOccurred(Exception exception, RequestType requestType);
}
