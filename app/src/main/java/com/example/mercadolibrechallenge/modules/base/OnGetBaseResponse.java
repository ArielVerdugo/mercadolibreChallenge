package com.example.mercadolibrechallenge.modules.base;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OnGetBaseResponse<T> implements SingleObserver<Response<T>> {

    protected Activity activity;
    protected Disposable mSubscription;
    protected GetBaseCallback<T> callback;

    public OnGetBaseResponse(Activity activity, GetBaseCallback<T> callback) {
        this.activity = activity;
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

    @Override
    public void onError(Throwable e) {

        if (e instanceof ConnectException) {
            callback.errorConnection();
            Log.e(TAG, "error " + e.getClass() + e.getMessage(), e);
        } else {
            callback.errorDefault();
            Log.e(TAG, "error " + e.getClass() + e.getMessage(), e);
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

}
