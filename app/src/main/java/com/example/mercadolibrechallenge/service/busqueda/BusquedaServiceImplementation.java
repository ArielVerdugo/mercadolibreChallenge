package com.example.mercadolibrechallenge.service.busqueda;

import com.example.mercadolibrechallenge.model.ProductosResponse;
import com.example.mercadolibrechallenge.service.base.BaseService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class BusquedaServiceImplementation extends BaseService {

    private BusquedaServiceInterface productosService;

    public BusquedaServiceImplementation(){
        super();
        productosService = retrofit.create(BusquedaServiceInterface.class);

    }

    public Single<Response<ProductosResponse>>getProductos(String producto){

        return productosService.getProductos(producto).subscribeOn(Schedulers.io());

    }
}
