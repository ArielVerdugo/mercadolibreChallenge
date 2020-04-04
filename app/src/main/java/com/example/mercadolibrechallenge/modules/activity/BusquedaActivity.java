package com.example.mercadolibrechallenge.modules.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.modules.adapter.BusquedaRecyclerViewAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import static com.example.mercadolibrechallenge.utils.Constants.FILTRO;


public class BusquedaActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener{

    private CarouselView carouselView;
    private int[] sampleImagesCarousel = {R.drawable.zapa8, R.drawable.zapa7, R.drawable.zapa2};

    private SearchView search;

    private RecyclerView rvHardcodeImages;
    private BusquedaRecyclerViewAdapter recyclerImagenesAdapter;


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

        //TODO RECYCLER
        /*ArrayList<Integer> sampleImagesRecycler = new ArrayList<>();
        sampleImagesRecycler.add(R.drawable.heladera);

        rvHardcodeImages = findViewById(R.id.rvImagenes);
        LinearLayoutManager llmImagenes = new LinearLayoutManager(getApplicationContext());
        rvHardcodeImages.setLayoutManager(llmImagenes);

        recyclerImagenesAdapter = new BusquedaRecyclerViewAdapter(sampleImagesRecycler, this);
        rvHardcodeImages.setAdapter(recyclerImagenesAdapter);*/


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent productosIntent = new Intent(this, ProductosActivity.class);
        productosIntent.putExtra(FILTRO, query);
        startActivity(productosIntent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}
