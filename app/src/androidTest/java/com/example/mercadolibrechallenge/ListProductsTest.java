package com.example.mercadolibrechallenge;

import android.app.Activity;
import android.view.View;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.rule.ActivityTestRule;

import com.example.mercadolibrechallenge.model.s.Envio;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;
import com.example.mercadolibrechallenge.service.busqueda.BusquedaServiceImplementation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

//import androidx.test.ext.junit.runners.*;

import static org.mockito.Mockito.doNothing;

public class ListProductsTest {

   /*@Rule
   public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();*/

    @Rule
    public ActivityTestRule<ProductosActivity>mActivityTestRule = new ActivityTestRule<ProductosActivity>(ProductosActivity.class);

    ProductosActivity pActivity = null;

   /*@InjectMocks
   BusquedaServiceImplementation busquedaService;

   @Mock
   ProductosActivity productosActivity;

   @Mock
   Producto producto;

   Single<Response<ProductosResponse>>testProductos;*/



   @Before
   public void setUp(){
       //MockitoAnnotations.initMocks(this);
       pActivity = mActivityTestRule.getActivity();
   }

   @Before
   public void initMocks(){
       //TODO OBJETO VACIO
       Envio envio = new Envio();
       //producto = new Producto("MLA811601010",255.63,"nuevo","imagen","perchero",envio,20,10);
       Activity a = new Activity();
   }

    @Test
    public void testViews(){
       View layoutView = pActivity.findViewById(R.id.layoutSinDatos);
        doNothing().when(pActivity).invokeBusquedaService("Heladera");
        Assert.assertEquals (layoutView.getVisibility(),View.GONE);
    }


   /*@Test
    public void getSomethingWithCorrectParams(){
        Assert.assertNotEquals (busquedaService.getProductos("heladera"), is(notNullValue()));
    }*/

    /*@Test
    public void setProductAdapterAfetCallService(){

        doNothing().when(productosActivity).invokeBusquedaService("Heladera");
        Assert.assertNotEquals(productosActivity.adapterProducto.getItemCount(), is(notNullValue()));


    }*/

    /*@Test
    public void getListProductsSuccesss(){
        Envio envio = new Envio();
        Producto producto = new Producto("MLA811601010",255.63,"nuevo","imagen","perchero",envio,20,10);
        List<Producto>listProd = new ArrayList<>();
        listProd.add(producto);

        busquedaService.getProductos("heladera");
        //Mockito.doReturn(testProductos).when(productosActivity).invokeBusquedaService("Heladera");

        doNothing().when(productosActivity).invokeBusquedaService("Heladera");
        Assert.assertEquals (productosActivity.layoutSinDatos.getVisibility(),View.GONE);
    }*/



   @BeforeClass
    public static void setUpRxSchedulers(){
      final Scheduler inmediate = new Scheduler() {
          @Override
          public Worker createWorker() {
              return new ExecutorScheduler.ExecutorWorker(new Executor() {
                  @Override
                  public void execute(Runnable command) {
                    command.run();
                  }
              }, true);
          }
      };

       RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> inmediate);
       RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> inmediate);
       RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> inmediate);
       RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> inmediate);
       RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> inmediate);
       RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> inmediate);
       RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> inmediate);

   }

   @After
    public void tearDown() throws Exception{
       pActivity = null;
   }
}
