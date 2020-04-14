package com.example.mercadolibrechallenge.service.busqueda;
import android.app.Activity;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.Contract.ProductoContract;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.modules.base.OnGetBaseResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class BusquedaModel implements ProductoContract.Model {

    private ProductoContract.View view;

    public BusquedaModel(ProductoContract.View view){
        this.view = view;
    }


    @Override
    public void getProductos(String producto, GetBaseCallback<ProductosResponse> callback, Activity activity) {
        BusquedaServiceImplementation busquedaService = new BusquedaServiceImplementation();
        busquedaService.getProductos(producto).observeOn(AndroidSchedulers.mainThread())
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
                .subscribe(new OnGetBaseResponse(activity, callback));
    }
}