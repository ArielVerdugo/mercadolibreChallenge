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
import com.example.mercadolibrechallenge.model.s.DetalleResponse;
import com.example.mercadolibrechallenge.modules.adapter.AtributosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.adapter.PhotosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.modules.base.OnGetBaseResponse;
import com.example.mercadolibrechallenge.service.detalle.DetalleServiceImplementation;
import com.example.mercadolibrechallenge.utils.BaseFunctions;
import com.example.mercadolibrechallenge.utils.Format;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.example.mercadolibrechallenge.utils.Constants.ID_PRODUCTO;

public class DetalleActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        findComponents();
        Intent mIntent = getIntent();
        idProducto = mIntent.getStringExtra(ID_PRODUCTO);
        invokeDetalleService(idProducto);

    }

    private void findComponents() {
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



    private void invokeDetalleService(String idProducto) {

        GetBaseCallback callback = new GetBaseCallback<DetalleResponse>() {
            @Override
            public void success(DetalleResponse response) {
                productTitle.setText(response.getTitle());
                productPrice.setText("$" + Format.formatDecimal(response.getPrice()));

                atributosAdapter = new AtributosRecyclerViewAdapter(DetalleActivity.this,response.getAtributos());
                rvAtributos.setAdapter(atributosAdapter);

                photosAdapter = new PhotosRecyclerViewAdapter(DetalleActivity.this,response.getPhotos());
                rvPhotos.setAdapter(photosAdapter);

            }

            @Override
            public void error400() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
            }

            @Override
            public void errorDefault() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                layoutData.setVisibility(View.GONE);
            }

            @Override
            public void errorConnection() {
                layoutData.setVisibility(View.GONE);
                layoutErrorInternet.setVisibility(View.VISIBLE);
                retrySearch();
            }
        };


        DetalleServiceImplementation detalleServiceImplementation = new DetalleServiceImplementation();
        detalleServiceImplementation.getDetalle(idProducto).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        stopLoading();
                    }
                })
                .subscribe(new OnGetBaseResponse(DetalleActivity.this, callback));
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
                invokeDetalleService(idProducto);
                layoutData.setVisibility(View.VISIBLE);
            }
        });
    }


}
