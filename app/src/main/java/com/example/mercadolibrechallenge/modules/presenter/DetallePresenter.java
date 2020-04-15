package com.example.mercadolibrechallenge.modules.presenter;

import com.example.mercadolibrechallenge.model.DetalleResponse;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.modules.contract.DetalleContract;
import com.example.mercadolibrechallenge.service.detalle.DetalleModel;

public class DetallePresenter implements DetalleContract.Presenter, GetBaseCallback {

    private DetalleContract.Model detalleModel;
    private DetalleContract.View detalleView;

    public DetallePresenter(DetalleContract.View detalleView) {
        this.detalleView = detalleView;
        detalleModel = new DetalleModel();
    }

    @Override
    public void requestDataDetalle(String query) {
        detalleView.showLoading();
        detalleModel.getDetalle(query,this);
    }

    @Override
    public void success(Object response) {
        detalleView.stopLoading();
        detalleView.onDetalleResponseSuccess((DetalleResponse) response);
    }

    @Override
    public void error400() {
        detalleView.stopLoading();
        detalleView.onDetalleResponseFailure();
    }

    @Override
    public void errorDefault() {
        detalleView.stopLoading();
        detalleView.onDetalleResponseError();
    }

    @Override
    public void errorConnection() {
        detalleView.stopLoading();
        detalleView.onDetalleResponseFailureConnection();
    }

    @Override
    public void onDestroy() {
        detalleModel = null;
    }
}
