package com.example.mercadolibrechallenge.model.s;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Envio {
    @SerializedName("free_shipping")
    @Expose
    private Boolean isFree;

    @SerializedName("store_pick_up")
    @Expose
    private Boolean isPickUp;

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Boolean getPickUp() {
        return isPickUp;
    }

    public void setPickUp(Boolean pickUp) {
        isPickUp = pickUp;
    }
}
