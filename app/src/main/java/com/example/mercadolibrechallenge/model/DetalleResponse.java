package com.example.mercadolibrechallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String image;

    @SerializedName("initial_quantity")
    @Expose
    private int initial_quantity;

    @SerializedName("sold_quantity")
    @Expose
    private int sold_quantity;

    @SerializedName("available_quantity")
    @Expose
    private int available_quantity;

    @SerializedName("attributes")
    @Expose
    private List<Atributo> atributos;

    @SerializedName("pictures")
    @Expose
    private List<PhotoResponse> photos;

    @SerializedName("shipping")
    @Expose
    private Envio shiping;

    public Envio getShiping() {
        return shiping;
    }

    public void setShiping(Envio shiping) {
        this.shiping = shiping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getInitial_quantity() {
        return initial_quantity;
    }

    public void setInitial_quantity(int initial_quantity) {
        this.initial_quantity = initial_quantity;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<PhotoResponse> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoResponse> photos) {
        this.photos = photos;
    }
}
