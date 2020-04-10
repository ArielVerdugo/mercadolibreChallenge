package com.example.mercadolibrechallenge.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import static com.example.mercadolibrechallenge.utils.Constants.SHIPPING_COST;
import static com.example.mercadolibrechallenge.utils.Constants.SHIPPING_FREE;


public class BaseFunctions {

    public static String getShipping(Boolean value){
        String shipping;

        if(!value){
            shipping = SHIPPING_COST;
        }else{
            shipping = SHIPPING_FREE;
        }
        return shipping;
    }

    public static void setPhotos(Context context,String img, ImageView view) {
        Glide.with(context)
                .load(img)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(view);
    }
}
