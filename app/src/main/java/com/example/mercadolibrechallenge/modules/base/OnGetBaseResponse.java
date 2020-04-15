package com.example.mercadolibrechallenge.modules.base;

import android.app.Activity;
import android.util.Log;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.internal.http2.ConnectionShutdownException;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OnGetBaseResponse<T> implements SingleObserver<Response<T>> {

    protected Disposable mSubscription;
    protected GetBaseCallback<T> callback;

    public OnGetBaseResponse(GetBaseCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mSubscription = d;
    }

    @Override
    public void onSuccess(Response<T> tResponse) {
        if (tResponse.isSuccessful()) {
            success(tResponse);
        } else {
            serviceErrors(tResponse);
        }
        mSubscription.dispose();

    }

    protected void success(Response<T> response) {
        callback.success(response.body());
    }

    protected void serviceErrors(Response<T> response) {
        switch (response.code()) {
            case 400:
                callback.error400();
                Log.e(TAG, "error " + response.code());
                break;
            default:
                callback.errorDefault();
                Log.e(TAG, "error " + response.code());
                break;
        }
    }


    @Override
    public void onError(Throwable e) {

        if (e instanceof ConnectException || e instanceof UnknownHostException  ||  e instanceof ConnectionShutdownException) {
            callback.errorConnection();
            Log.e(TAG, "error " + e.getClass().getName(), e);
        } else {
            callback.errorDefault();
            Log.e(TAG, "error " + e.getClass().getName(), e);
        }
        mSubscription.dispose();
    }

}
