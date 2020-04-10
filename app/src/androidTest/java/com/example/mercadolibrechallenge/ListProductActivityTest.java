package com.example.mercadolibrechallenge;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

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


public class ListProductActivityTest {

    @Rule
    public ActivityTestRule<ProductosActivity> mActivityTestRule = new ActivityTestRule<>(ProductosActivity.class);

    ProductosActivity pActivity;

    @Mock
    ProductosResponse response;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testViews(){
        Producto p = new Producto("MLA811601010",255.63,"http://mlb-s1-p.mlstatic.com/833834-MLA31348110414_072019-I.jpg","imagen","perchero",null,20,10);
        List<Producto> productos = new ArrayList<>();
        productos.add(p);

        Mockito.when(response.getProductos()).thenReturn(productos);

        View view = pActivity.findViewById(R.id.layoutSinDatos);
        //doNothing().when(pActivity).invokeBusquedaService("asddsaadsasd");
        Assert.assertEquals (view.getVisibility(),View.GONE);
    }



    @After
    public void tearDown()throws Exception{
        pActivity = null;
    }
}
