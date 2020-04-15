package com.example.mercadolibrechallenge;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mercadolibrechallenge.model.s.Envio;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;


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
    List<Producto>productos = new ArrayList<>();



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
        producto = new Producto("producto_inexistente",25.02,"Viejo","imagen_inexistente","no existe",envio,120,110);
        productos = new ArrayList<>();
        productos.add(producto);
        response.setProductos(productos);


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
