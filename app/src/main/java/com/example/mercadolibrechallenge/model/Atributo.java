package com.example.mercadolibrechallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Atributo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("value_struct")
    @Expose
    private AtributoConfiguracion atributoConfiguracion;

    @SerializedName("value_name")
    @Expose
    private String descripcionName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtributoConfiguracion getAtributoConfiguracion() {
        return atributoConfiguracion;
    }

    public void setAtributoConfiguracion(AtributoConfiguracion atributoConfiguracion) {
        this.atributoConfiguracion = atributoConfiguracion;
    }

    public String getDescripcionName() {
        return descripcionName;
    }

    public void setDescripcionName(String descripcionName) {
        this.descripcionName = descripcionName;
    }

}
