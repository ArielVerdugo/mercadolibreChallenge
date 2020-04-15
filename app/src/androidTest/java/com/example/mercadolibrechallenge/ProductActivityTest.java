package com.example.mercadolibrechallenge;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.rule.ActivityTestRule;

import com.example.mercadolibrechallenge.model.Envio;
import com.example.mercadolibrechallenge.model.Producto;
import com.example.mercadolibrechallenge.model.ProductosResponse;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


public class ProductActivityTest {

    @Rule
    public ActivityTestRule<ProductosActivity> mActivityTestRule = new ActivityTestRule<>(ProductosActivity.class);

    ProductosActivity pActivity;

    @Mock
    ProductosResponse response;

    private View viewErrorConeccion;
    private View viewErrorServicio;
    private View viewSinData;
    private RecyclerView rvProductos;

    private Producto producto;
    private Envio envio;




    @Before
    public void setUp() {
        pActivity = mActivityTestRule.getActivity();
        viewErrorConeccion = pActivity.findViewById(R.id.layoutErrorConeccion);
        viewErrorServicio = pActivity.findViewById(R.id.layoutErrorServicio);
        viewSinData = pActivity.findViewById(R.id.layoutSinDatos);
        rvProductos = pActivity.findViewById(R.id.rv_resultados_busqueda);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewProductoDesconocido(){

        //Mockito.when(response.getProductos()).thenReturn(productos);
        Assert.assertEquals (viewSinData.getVisibility(),View.VISIBLE);
        Assert.assertEquals (rvProductos.getVisibility(),View.GONE);
        Assert.assertEquals (viewErrorServicio.getVisibility(),View.GONE);
        Assert.assertEquals (viewErrorConeccion.getVisibility(),View.GONE);
    }

    @Test
    public void testViewProductoExistente(){
        envio = new Envio();
        producto = new Producto();
        List<Producto>productos = new ArrayList<>();
        productos.add(producto);
        //response.setProductos(productos);

        Mockito.when(response.getProductos()).thenReturn(productos);

        Assert.assertEquals (viewSinData.getVisibility(),View.GONE);
        Assert.assertEquals (rvProductos.getVisibility(),View.VISIBLE);
        Assert.assertEquals (viewErrorServicio.getVisibility(),View.GONE);
        Assert.assertEquals (viewErrorConeccion.getVisibility(),View.GONE);


    }

    @Test
    public void testLoading(){
        View viewLoading = pActivity.findViewById(R.id.carga_resultados);
        Assert.assertEquals(viewLoading.getVisibility(),View.GONE);
    }

    @Test
    public void testViewSinConeccion(){
        WifiManager wifiManager = (WifiManager) pActivity.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        View viewErrorConeccion = pActivity.findViewById(R.id.layoutErrorConeccion);
        Assert.assertEquals (viewErrorConeccion.getVisibility(),View.GONE);

    }



    @After
    public void tearDown()throws Exception{
        pActivity = null;
    }
}
