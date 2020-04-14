package com.example.mercadolibrechallenge.modules.Contract;

import android.app.Activity;

import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;

public interface ProductoContract {

    interface Presenter {
    }


    interface Model {
        void getProductos(String producto, GetBaseCallback<ProductosResponse> callback, Activity activity);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void findComponents();

        void retrySearch();
    }

}
