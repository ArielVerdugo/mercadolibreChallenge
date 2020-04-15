package com.example.mercadolibrechallenge.service.detalle;

import android.app.Activity;

import com.example.mercadolibrechallenge.model.DetalleResponse;
import com.example.mercadolibrechallenge.modules.Contract.DetalleContract;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.modules.base.OnGetBaseResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class DetalleModel implements DetalleContract.Model {

    private DetalleContract.View view;

    public DetalleModel(DetalleContract.View view){
        this.view = view;
    }

    @Override
    public void getDetalle(String id, GetBaseCallback<DetalleResponse> callback, Activity activity) {
        DetalleServiceImplementation detalleServiceImplementation = new DetalleServiceImplementation();
        detalleServiceImplementation.getDetalle(id).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.stopLoading();
                    }
                })
                .subscribe(new OnGetBaseResponse(callback));
    }
}
