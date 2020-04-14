package com.example.mercadolibrechallenge.modules.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.Contract.ProductoContract;
import com.example.mercadolibrechallenge.modules.adapter.ProductosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
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

    private ProductoContract.Model model;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Intent mIntent = getIntent();
        this.producto = mIntent.getStringExtra(FILTRO);

        findComponents();
        model = new BusquedaModel(this);
        invokeBusquedaService(producto);

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

    private void invokeBusquedaService(final String producto){
        GetBaseCallback callback = new GetBaseCallback<ProductosResponse>() {
            @Override
            public void success(ProductosResponse response) {

                if (!response.getProductos().isEmpty())
                {
                    productos = response.getProductos();
                    adapterProducto = new ProductosRecyclerViewAdapter(response.getProductos(),ProductosActivity.this ,ProductosActivity.this);
                    rvProductos.setAdapter(adapterProducto);
                }
                else{
                    layoutSinDatos.setVisibility(View.VISIBLE);
                    rvProductos.setVisibility(View.GONE);
                }

            }

            @Override
            public void error400() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                rvProductos.setVisibility(View.GONE);
            }

            @Override
            public void errorDefault() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                rvProductos.setVisibility(View.GONE);
            }


            @Override
            public void errorConnection() {
                layoutErrorInternet.setVisibility(View.VISIBLE);
                rvProductos.setVisibility(View.GONE);
                retrySearch();
            }
        };

        model.getProductos(producto,callback,this);


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
                invokeBusquedaService(producto);
                rvProductos.setVisibility(View.VISIBLE);
            }
        });
    }
}
