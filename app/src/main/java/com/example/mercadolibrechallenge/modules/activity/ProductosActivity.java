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
import com.example.mercadolibrechallenge.model.Producto;
import com.example.mercadolibrechallenge.model.ProductosResponse;
import com.example.mercadolibrechallenge.modules.contract.ProductoContract;
import com.example.mercadolibrechallenge.modules.adapter.ProductosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.presenter.ProductoPresenter;
import com.example.mercadolibrechallenge.service.busqueda.BusquedaModel;
import com.example.mercadolibrechallenge.service.busqueda.OnItemClickListener;
import com.example.mercadolibrechallenge.utils.BaseFunctions;

import java.util.List;

import static com.example.mercadolibrechallenge.utils.Constants.FILTRO;
import static com.example.mercadolibrechallenge.utils.Constants.ID_PRODUCTO;


public class ProductosActivity extends AppCompatActivity implements OnItemClickListener, ProductoContract.View {

    private String producto;
    private View layoutErrorInternet;
    private View layoutErrorServicio;
    private View layoutSinDatos;

    private List<Producto> productos;

    private ImageView backImage;
    private RecyclerView rvProductos;
    private ProductosRecyclerViewAdapter adapterProducto;

    private TextView productHeader;
    private ProgressBar progressBar;
    private Button retryButton;

    private ProductosResponse productosResponse;
    private ProductoContract.Presenter productPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Intent mIntent = getIntent();
        producto = mIntent.getStringExtra(FILTRO);

        findComponents();
        productPresenter = new ProductoPresenter(this,new BusquedaModel());
        productPresenter.requestDataProduct(producto);

    }

    public void findComponents() {

        layoutErrorInternet = findViewById(R.id.layoutErrorConeccion);
        retryButton = layoutErrorInternet.findViewById(R.id.botonReintentar);

        layoutErrorServicio = findViewById(R.id.layoutErrorServicio);
        layoutSinDatos = findViewById(R.id.layoutSinDatos);


        rvProductos = findViewById(R.id.rv_resultados_busqueda);
        LinearLayoutManager llmImagenes = new LinearLayoutManager(getApplicationContext());
        rvProductos.setLayoutManager(llmImagenes);

        progressBar = findViewById(R.id.carga_resultados);

        View myLayout = findViewById( R.id.header_productos);
        backImage = myLayout.findViewById(R.id.returnView);
        BaseFunctions.backScreen(backImage,this);
        productHeader = myLayout.findViewById(R.id.head_product);
        productHeader.setText(producto);

    }

    @Override
    public void onProductoResponseSuccess(ProductosResponse response) {
        if (!response.getProductos().isEmpty())
        {
            productos = response.getProductos();
            adapterProducto = new ProductosRecyclerViewAdapter(response.getProductos(),ProductosActivity.this ,ProductosActivity.this);
            rvProductos.setAdapter(adapterProducto);
            rvProductos.setVisibility(View.VISIBLE);
        }
        else{
            layoutSinDatos.setVisibility(View.VISIBLE);
            rvProductos.setVisibility(View.GONE);
        }
    }

    @Override
    public void onProductoResponseFailure() {
        layoutErrorServicio.setVisibility(View.VISIBLE);
        rvProductos.setVisibility(View.GONE);
    }

    @Override
    public void onProductoResponseFailureConnection() {
        layoutErrorInternet.setVisibility(View.VISIBLE);
        rvProductos.setVisibility(View.GONE);
        retrySearch();

    }

    @Override
    public void onProductoResponseError() {
        layoutErrorServicio.setVisibility(View.VISIBLE);
        rvProductos.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        BaseFunctions.redirectActivityWithString(this,DetalleActivity.class,ID_PRODUCTO,productos.get(position).getId());
    }


    public void showLoading() {
        layoutErrorInternet.setVisibility(View.GONE);
        layoutErrorServicio.setVisibility(View.GONE);
        layoutSinDatos.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void retrySearch(){
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.requestDataProduct(producto);
            }
        });
    }
}
