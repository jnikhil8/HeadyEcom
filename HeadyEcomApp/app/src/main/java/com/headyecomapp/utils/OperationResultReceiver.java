package com.headyecomapp.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 */
public class OperationResultReceiver extends ResultReceiver {
    private Receiver mReceiver;

    public OperationResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }
}
