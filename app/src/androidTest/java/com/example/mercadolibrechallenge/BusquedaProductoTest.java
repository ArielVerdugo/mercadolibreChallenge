package com.example.mercadolibrechallenge;

import android.content.Context;
import android.net.wifi.WifiManager;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mercadolibrechallenge.modules.activity.BusquedaActivity;

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
public class BusquedaProductoTest {

    private String stringValid;
    private String stringInvalid;

    @Rule
    public ActivityTestRule<BusquedaActivity> activityRule = new ActivityTestRule<>(BusquedaActivity.class);

    BusquedaActivity pActivity;
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
    public void initIntvalidString() {
        // producto invalido
        stringInvalid = "ijasfdihsdfgefdihofaijpsda";
    }

    @Before
    public void initIntValues() {
        // obtener referencia de la activity
        pActivity = activityRule.getActivity();

        // referencia para habilitar o cortar el wifi
        wifiManager = (WifiManager) pActivity.getSystemService(Context.WIFI_SERVICE);

        // tiempo de espera
        middleTime = 5000;
        shortTime = 1000;
        longTime = 13000;
    }




    @Test
    public void findValidProduct() throws InterruptedException {
        wifiManager.setWifiEnabled(true);
        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringValid));
        Thread.sleep(middleTime);

        onView(withId((R.id.rv_resultados_busqueda)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void findInvalidProduct() throws InterruptedException {
        wifiManager.setWifiEnabled(true);
        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringInvalid));
        Thread.sleep(middleTime);

        onView(withId((R.id.layoutSinDatos)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void findProductNoConnection() throws InterruptedException {
        wifiManager.setWifiEnabled(false);

        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringValid));
        Thread.sleep(middleTime);
        onView(withId((R.id.layoutErrorConeccion)))
                .check(matches(isDisplayed()));
        wifiManager.setWifiEnabled(true);
    }

    @Test
    public void testLoading()throws InterruptedException{
        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringValid));
        Thread.sleep(shortTime);
        onView(withId((R.id.carga_resultados)))
                .check(matches(isDisplayed()));
    }


    @Test
    public void retryButton()throws InterruptedException{
        wifiManager.setWifiEnabled(false);
        onView(withId(R.id.buscar))
                .perform(SearchViewActionExtension.Companion.submitText(stringValid));
        Thread.sleep(shortTime);
        wifiManager.setWifiEnabled(true);
        Thread.sleep(longTime);
        onView(withId(R.id.botonReintentar))
                .perform(click());
        Thread.sleep(middleTime);
        onView(withId((R.id.rv_resultados_busqueda)))
                .check(matches(isDisplayed()));
    }
}


