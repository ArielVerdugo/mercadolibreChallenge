package com.example.mercadolibrechallenge.service.detalle;

import com.example.mercadolibrechallenge.model.s.DetalleResponse;
import com.example.mercadolibrechallenge.service.base.BaseService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class DetalleServiceImplementation extends BaseService {

    private DetalleServiceInterface detalleService;

    public DetalleServiceImplementation(){
        super();
        detalleService = retrofit.create(DetalleServiceInterface.class);
    }

    public Single<Response<DetalleResponse>> getDetalle(String id){
        return detalleService.getDetalle(id).subscribeOn(Schedulers.io());
    }
}
