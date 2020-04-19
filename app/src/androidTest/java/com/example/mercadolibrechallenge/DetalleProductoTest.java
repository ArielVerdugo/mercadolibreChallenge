package com.example.mercadolibrechallenge;

import android.content.Context;
import android.net.wifi.WifiManager;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mercadolibrechallenge.modules.activity.BusquedaActivity;
import com.example.mercadolibrechallenge.modules.activity.ProductosActivity;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetalleProductoTest {

    private String stringValid;

    @Rule
    public ActivityTestRule<BusquedaActivity> activityRuleBusqueda = new ActivityTestRule<>(BusquedaActivity.class);

    @Rule
    public ActivityTestRule<ProductosActivity> activityRuleProducto = new ActivityTestRule<>(ProductosActivity.class);


    BusquedaActivity busquedaActivity;
    ProductosActivity productosActivity;
    Integer longTime;
    Integer middleTime;
    Integer shortTime;
    WifiManager wifiManager;

    @Before
    public void initValidString() {
        // producto valido
        stringValid = "heladera";
    }

    @Before
    public void initIntValues() {
        // obtener referencia de la activity busqueda
        //busquedaActivity = activityRuleBusqueda.getActivity();

        // obtener referencia de la activity detalle
        productosActivity = activityRuleProducto.getActivity();

        // referencia para habilitar o cortar el wifi
        wifiManager = (WifiManager) productosActivity.getSystemService(Context.WIFI_SERVICE);

        // tiempo de espera
        middleTime = 5000;
        shortTime = 1000;
        longTime = 13000;
    }

    @Before
    public void initSearchProducto() throws InterruptedException{
        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringValid));
        Thread.sleep(longTime);
    }


    @Test
    public void selectProduct()throws InterruptedException{
        onView(withId(R.id.rv_resultados_busqueda))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep(middleTime);

        onView(withId((R.id.layoutConData)))
                .check(matches(isDisplayed()));
    }


    @Test
    public void selectProductNoConnection(){
        wifiManager.setWifiEnabled(false);
        onView(withId(R.id.rv_resultados_busqueda))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId((R.id.layoutErrorConeccion)))
                .check(matches(isDisplayed()));
        wifiManager.setWifiEnabled(true);
    }

    @Test
    public void testLoading(){
        onView(withId(R.id.rv_resultados_busqueda))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId((R.id.carga_resultados)))
                .check(matches(isDisplayed()));
    }


    @Test
    public void retryButton()throws InterruptedException{
        wifiManager.setWifiEnabled(false);
        onView(withId(R.id.rv_resultados_busqueda))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        wifiManager.setWifiEnabled(true);
        Thread.sleep(longTime);
        onView(withId(R.id.botonReintentar))
                .perform(click());
        Thread.sleep(longTime);
        onView(withId((R.id.layoutConData)))
                .check(matches(isDisplayed()));
    }

}


