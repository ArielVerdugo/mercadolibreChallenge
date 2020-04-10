package com.example.mercadolibrechallenge.modules.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.adapter.ProductosRecyclerViewAdapter;
import com.example.mercadolibrechallenge.modules.base.GetBaseCallback;
import com.example.mercadolibrechallenge.modules.base.OnGetBaseResponse;
import com.example.mercadolibrechallenge.service.busqueda.BusquedaServiceImplementation;
import com.example.mercadolibrechallenge.service.busqueda.OnItemClickListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.example.mercadolibrechallenge.utils.Constants.FILTRO;
import static com.example.mercadolibrechallenge.utils.Constants.ID_PRODUCTO;


public class ProductosActivity extends AppCompatActivity implements OnItemClickListener {

    private String producto;
    private View layoutErrorInternet;
    private View layoutErrorServicio;
    public View layoutSinDatos;

    public List<Producto> productos;

    public RecyclerView rvProductos;
    public ProductosRecyclerViewAdapter adapterProducto;
    private SearchView productoBusqueda;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        findComponents();

        Intent mIntent = getIntent();
        this.producto = mIntent.getStringExtra(FILTRO);
        invokeBusquedaService(producto);


    }

    private void findComponents() {

        layoutErrorInternet = findViewById(R.id.layoutErrorConeccion);
        layoutErrorServicio = findViewById(R.id.layoutErrorServicio);
        layoutSinDatos = findViewById(R.id.layoutSinDatos);


        rvProductos = findViewById(R.id.rv_resultados_busqueda);
        LinearLayoutManager llmImagenes = new LinearLayoutManager(getApplicationContext());
        rvProductos.setLayoutManager(llmImagenes);


        View myLayout = findViewById( R.id.header_busqueda);
        productoBusqueda = myLayout.findViewById(R.id.buscar);
        productoBusqueda.setQuery(producto,true);



    }

    public void invokeBusquedaService(final String producto){
        GetBaseCallback callback = new GetBaseCallback<ProductosResponse>() {
            @Override
            public void success(ProductosResponse response) {

                if (!response.getProductos().isEmpty())
                {
                    layoutErrorServicio.setVisibility(View.GONE);
                    layoutErrorInternet.setVisibility(View.GONE);
                    layoutSinDatos.setVisibility(View.GONE);
                    productos = response.getProductos();
                    adapterProducto = new ProductosRecyclerViewAdapter(response.getProductos(),ProductosActivity.this ,ProductosActivity.this);
                    rvProductos.setAdapter(adapterProducto);
                }
                else{
                    layoutSinDatos.setVisibility(View.VISIBLE);
                    layoutErrorServicio.setVisibility(View.GONE);
                    layoutErrorInternet.setVisibility(View.GONE);
                    rvProductos.setVisibility(View.GONE);
                }

            }

            @Override
            public void error400() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                layoutSinDatos.setVisibility(View.GONE);
                layoutErrorInternet.setVisibility(View.GONE);
                rvProductos.setVisibility(View.GONE);
            }

            @Override
            public void errorDefault() {
                layoutErrorServicio.setVisibility(View.VISIBLE);
                layoutSinDatos.setVisibility(View.GONE);
                layoutErrorInternet.setVisibility(View.GONE);
                rvProductos.setVisibility(View.GONE);
            }


            @Override
            public void errorConnection() {
                layoutErrorInternet.setVisibility(View.VISIBLE);
                layoutErrorServicio.setVisibility(View.GONE);
                layoutSinDatos.setVisibility(View.GONE);
                rvProductos.setVisibility(View.GONE);
            }
        };

        BusquedaServiceImplementation busquedaService = new BusquedaServiceImplementation();
        busquedaService.getProductos(producto).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new OnGetBaseResponse(ProductosActivity.this, callback));

    }

    @Override
    public void onItemClick(int position) {
        Intent detalleProduct = new Intent(this, DetalleActivity.class);
        detalleProduct.putExtra(ID_PRODUCTO,productos.get(position).getId());
        startActivity(detalleProduct);
    }
}
