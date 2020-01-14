package com.headyecomapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.headyecomapp.AppController;
import com.headyecomapp.R;
import com.headyecomapp.constants.RequestType;
import com.headyecomapp.interfaces.IDataResponseListener;

import org.json.JSONObject;


/**
 *
 */
public class DownloadUtils {

    public static void makeJsonRequestWithGetMethod(final Context context,
                                                    String url,
                                                    final JSONObject jsonRequestObject,
                                                    final IDataResponseListener dataResponseListener,
                                                    final RequestType requestType) {
        if (AppUtils.isNetworkAvailable(context)) {
            final ProgressDialog progressDialog = getProgressDialog(context);
            progressDialog.show();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                    jsonRequestObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    progressDialog.dismiss();
                    dataResponseListener.onJsonDataReceived(jsonObject, requestType);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressDialog.dismiss();
                        }
                    });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        } else {
            showNetworkNotAvailableDialog(context);
            dataResponseListener.onExceptionOccurred(new NetworkErrorException("No Network"),
                    requestType);
        }
    }


    private static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.MyTheme);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.progress_bar_drawable));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    private static void showNetworkNotAvailableDialog(Context context) {

        Toast.makeText(context, "Network not available", Toast
                .LENGTH_SHORT).show();
    }
}
