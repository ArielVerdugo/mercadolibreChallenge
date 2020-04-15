package com.example.mercadolibrechallenge.modules.contract;

import com.example.mercadolibrechallenge.model.ProductosResponse;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;

public interface ProductoContract {

    interface Presenter {

        void onDestroy();
        void requestDataProduct(String query);
    }


    interface Model {

        void getProductos(String producto, GetBaseCallback<ProductosResponse> callback);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void findComponents();

        void onProductoResponseSuccess(ProductosResponse response);

        void onProductoResponseFailure();

        void onProductoResponseFailureConnection();

        void onProductoResponseError();

        void retrySearch();
    }

}
