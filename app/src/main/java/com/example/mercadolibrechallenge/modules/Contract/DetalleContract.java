package com.example.mercadolibrechallenge.modules.Contract;

import android.app.Activity;

import com.example.mercadolibrechallenge.model.DetalleResponse;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;

public interface DetalleContract {

    interface Presenter {
    }


    interface Model {
        void getDetalle(String id, GetBaseCallback<DetalleResponse> callback, Activity activity);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void findComponents();

        void retrySearch();
    }
}
