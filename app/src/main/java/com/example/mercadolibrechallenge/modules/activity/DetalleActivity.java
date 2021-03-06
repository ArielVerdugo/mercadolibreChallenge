package com.example.mercadolibrechallenge.modules.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.DetalleResponse;
import com.example.mercadolibrechallenge.modules.contract.DetalleContract;
import com.example.mercadolibrechallenge.modules.adapter.AtributosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.adapter.PhotosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.presenter.DetallePresenter;
import com.example.mercadolibrechallenge.service.detalle.DetalleModel;
import com.example.mercadolibrechallenge.utils.BaseFunctions;
import com.example.mercadolibrechallenge.utils.Format;


import static com.example.mercadolibrechallenge.utils.Constants.ID_PRODUCTO;

public class DetalleActivity extends AppCompatActivity implements DetalleContract.View {

    private ImageView backImage;
    private TextView productTitle;
    private TextView productPrice;
    private ProgressBar progressBar;
    private Button retryButton;

    private String idProducto;


    private RecyclerView rvAtributos;
    private RecyclerView rvPhotos;

    private LinearLayoutManager linnearPhotos;
    private LinearLayoutManager linnearAtributos;

    private AtributosRecyclerViewAdapter atributosAdapter;
    private PhotosRecyclerViewAdapter photosAdapter;

    private View layoutErrorInternet;
    private View layoutErrorServicio;

    private View layoutData;
    private DetallePresenter detallePresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        findComponents();
        Intent mIntent = getIntent();
        idProducto = mIntent.getStringExtra(ID_PRODUCTO);
        detallePresenter = new DetallePresenter(this);
        detallePresenter.requestDataDetalle(idProducto);

    }

    public void findComponents() {
        productTitle = findViewById(R.id.titleProducto);
        productPrice = findViewById(R.id.priceProducto);


        rvAtributos = findViewById(R.id.rvAttributes);
        rvPhotos = findViewById(R.id.rvPhotos);
        layoutData = findViewById(R.id.groupView);
        linnearAtributos = new LinearLayoutManager(getApplicationContext());
        linnearPhotos = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        rvAtributos.setLayoutManager(linnearAtributos);
        rvPhotos.setLayoutManager(linnearPhotos);

        progressBar = findViewById(R.id.carga_resultados);

        layoutErrorInternet = findViewById(R.id.layoutErrorConeccion);
        retryButton = layoutErrorInternet.findViewById(R.id.botonReintentar);
        layoutErrorServicio = findViewById(R.id.layoutErrorServicio);
        View myLayout = findViewById( R.id.header_detalle);

        backImage = myLayout.findViewById(R.id.returnView);
        BaseFunctions.backScreen(backImage,this);

    }

    @Override
    public void onDetalleResponseSuccess(DetalleResponse response) {

        layoutData.setVisibility(View.VISIBLE);
        productTitle.setText(response.getTitle());
        productPrice.setText("$" + Format.formatDecimal(response.getPrice()));

        atributosAdapter = new AtributosRecyclerViewAdapter(DetalleActivity.this,response.getAtributos());
        rvAtributos.setAdapter(atributosAdapter);

        photosAdapter = new PhotosRecyclerViewAdapter(DetalleActivity.this,response.getPhotos());
        rvPhotos.setAdapter(photosAdapter);
    }

    @Override
    public void onDetalleResponseFailure() {
        layoutErrorServicio.setVisibility(View.VISIBLE);
        layoutData.setVisibility(View.GONE);
    }

    @Override
    public void onDetalleResponseFailureConnection() {
        layoutData.setVisibility(View.GONE);
        layoutErrorInternet.setVisibility(View.VISIBLE);
        retrySearch();
    }

    @Override
    public void onDetalleResponseError() {
        layoutErrorServicio.setVisibility(View.VISIBLE);
        layoutData.setVisibility(View.GONE);
    }


    public void showLoading() {
        layoutErrorInternet.setVisibility(View.GONE);
        layoutErrorServicio.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void retrySearch(){
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detallePresenter.requestDataDetalle(idProducto);
            }
        });
    }


}
