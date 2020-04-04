package com.example.mercadolibrechallenge.model.s;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Producto {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("price")
    @Expose
    private Double precio;

    @SerializedName("condition")
    @Expose
    private String condicion;

    @SerializedName("thumbnail")
    @Expose
    private String imagen;

    @SerializedName("title")
    @Expose
    private String titulo;

    @SerializedName("shipping")
    @Expose
    private Envio shipping;

    @SerializedName("available_quantity")
    @Expose
    private Integer cantidadDisponible;

    @SerializedName("sold_quantity")
    @Expose
    private Integer cantidadVendida;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Envio getShiping() {
        return shipping;
    }

    public void setShiping(Envio shiping) {
        this.shipping = shiping;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

}
