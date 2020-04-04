package com.example.mercadolibrechallenge.service.detalle;

import com.example.mercadolibrechallenge.model.s.DetalleResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetalleServiceInterface {
    @GET("/items")
    Single<Response<DetalleResponse>> getDetalle(@Query("id") String id);
}
