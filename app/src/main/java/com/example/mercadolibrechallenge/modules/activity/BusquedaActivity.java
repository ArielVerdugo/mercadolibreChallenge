package com.example.mercadolibrechallenge.modules.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.utils.BaseFunctions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import static com.example.mercadolibrechallenge.utils.Constants.FILTRO;


public class BusquedaActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    private CarouselView carouselView;
    private int[] sampleImagesCarousel = {R.drawable.zapa8, R.drawable.zapa7, R.drawable.zapa2};

    private SearchView search;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        findComponents();

    }

    private void findComponents(){

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImagesCarousel.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImagesCarousel[position]);
            }
        };

        carouselView.setImageListener(imageListener);
        View myLayout = findViewById( R.id.header_busqueda);
        search = myLayout.findViewById( R.id.buscar );
        search.setOnQueryTextListener(this);


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        BaseFunctions.redirectActivityWithString(this,ProductosActivity.class,FILTRO,query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}
