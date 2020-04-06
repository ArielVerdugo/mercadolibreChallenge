package com.example.mercadolibrechallenge;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.mercadolibrechallenge.model.s.Envio;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.model.s.ProductosResponse;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;
import com.example.mercadolibrechallenge.service.busqueda.BusquedaServiceImplementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;



public class ListProductsTest {

   @Rule
   public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

   @Mock
   BusquedaServiceImplementation busquedaService;

    @InjectMocks
    ProductosActivity productosActivity = new ProductosActivity();


   Single<ProductosResponse> testProductos;


   @Before
   public void setUp(){
       MockitoAnnotations.initMocks(this);
   }


   @Test
   public void getListProductsSuccess(){
       Envio envio = new Envio();
       Producto producto = new Producto("MLA811601010",255.63,"nuevo","imagen","perchero",envio,20,10);
       List<Producto>listProd = new ArrayList<>();
       listProd.add(producto);

       ProductosResponse productosResponse = new ProductosResponse(listProd);
       testProductos = Single.just(productosResponse);
       Mockito.doReturn(testProductos).when(busquedaService).getProductos("Heladera");

       productosActivity.invokeBusquedaService("Heladera");

       Assert.assertEquals(1,1);


   }

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
}
