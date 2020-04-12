package com.example.mercadolibrechallenge.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;

import static com.example.mercadolibrechallenge.utils.Constants.FILTRO;
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
                .apply(new RequestOptions().placeholder(R.drawable.ic_holder).error(R.drawable.ic_holder))
                .into(view);
    }

    public static void redirectActivityWithString(Context context, Class clase,String key,String value){
        Intent productosIntent = new Intent(context, clase);
        productosIntent.putExtra(key, value);
        context.startActivity(productosIntent);
    }

    public static void backScreen(View view,final Activity activity){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

}
