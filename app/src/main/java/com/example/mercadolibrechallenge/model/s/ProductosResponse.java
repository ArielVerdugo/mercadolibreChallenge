package com.example.mercadolibrechallenge.model.s;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductosResponse {

    @SerializedName("results")
    @Expose
    private List<Producto>productos;


    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductosResponse(List<Producto> productos) {
        this.productos = productos;
    }
}
