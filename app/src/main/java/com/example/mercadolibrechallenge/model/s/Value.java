package com.example.mercadolibrechallenge.model.s;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Value{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

}
