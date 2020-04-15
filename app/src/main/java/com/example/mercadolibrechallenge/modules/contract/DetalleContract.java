package com.example.mercadolibrechallenge.modules.contract;

import android.app.Activity;

import com.example.mercadolibrechallenge.model.DetalleResponse;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;

public interface DetalleContract {

    interface Presenter {
        void onDestroy();
        void requestDataDetalle(String query);
    }


    interface Model {
        void getDetalle(String id, GetBaseCallback<DetalleResponse> callback);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void findComponents();

        void onDetalleResponseSuccess(DetalleResponse response);

        void onDetalleResponseFailure();

        void onDetalleResponseFailureConnection();

        void onDetalleResponseError();

        void retrySearch();
    }
}
