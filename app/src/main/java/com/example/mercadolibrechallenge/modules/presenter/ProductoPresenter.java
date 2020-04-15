package com.example.mercadolibrechallenge.modules.presenter;

import com.example.mercadolibrechallenge.model.ProductosResponse;
import com.example.mercadolibrechallenge.modules.contract.ProductoContract;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.service.busqueda.BusquedaModel;


public class ProductoPresenter implements ProductoContract.Presenter, GetBaseCallback {

    private ProductoContract.View productoView;
    private ProductoContract.Model productoModel;


    public ProductoPresenter(ProductoContract.View productoView) {
        this.productoView = productoView;
        this.productoModel = new BusquedaModel();
    }

    @Override
    public void requestDataProduct(String query) {
        productoView.showLoading();
        productoModel.getProductos(query, this);
    }

    @Override
    public void success(Object response) {
        productoView.stopLoading();
        productoView.onProductoResponseSuccess((ProductosResponse) response);
    }

    @Override
    public void error400() {
        productoView.stopLoading();
        productoView.onProductoResponseFailure();
    }

    @Override
    public void errorDefault() {
        productoView.stopLoading();
        productoView.onProductoResponseError();
    }

    @Override
    public void errorConnection() {
        productoView.stopLoading();
        productoView.onProductoResponseFailureConnection();
    }

    @Override
    public void onDestroy() {
        productoModel = null;
    }
}
