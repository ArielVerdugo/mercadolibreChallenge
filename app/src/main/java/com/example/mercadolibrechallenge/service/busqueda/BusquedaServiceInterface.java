package com.example.mercadolibrechallenge.service.busqueda;

import com.example.mercadolibrechallenge.model.ProductosResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusquedaServiceInterface {

    @GET("/sites/MLA/search")
    Single<Response<ProductosResponse>>getProductos(@Query("q") String query);
}
