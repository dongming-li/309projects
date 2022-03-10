package com.example.matthewburke.promolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//import file with server getter methods

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        //String promoTitle = getCurrentPromoTitle();
        String promoTitle = "Offer1SEPARATEOffer2SEPARATEOffer3SEPARATE";
        String[] promos = promoTitle.split("SEPARATE");
        for (int i = 0; i < promoTitle.length(); i++){
            list.add(promos[i]);
        }

        list.add(promoTitle);


        list.add("Promo1");
        list.add("Promo2");
        list.add("Promo3");
        list.add("Promo4");
        list.add("Promo5");
        list.add("Promo6");
        list.add("Promo7");
        list.add("Promo8");
        list.add("Promo9");
        list.add("Promo10");
        list.add("Promo11");
        list.add("Promo12");
        list.add("Promo13");
        list.add("Promo14");
        list.add("Promo15");
        list.add("Promo16");
        list.add("Promo17");
        list.add("Promo18");
        list.add("Promo19");
        list.add("Promo20");
        list.add("Promo21");
        list.add("Promo22");
        list.add("Promo23");
        list.add("Promo24");

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
