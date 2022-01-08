package com.teganjennings.uncommonclothing.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teganjennings.uncommonclothing.Adapter.ClothesAdapter;
import com.teganjennings.uncommonclothing.Fragment.CouponCardFragment;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

import java.util.ArrayList;

public class JeansActivity extends AppCompatActivity  {

    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewJeansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeans_activity);

        recyclerViewTops();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private void recyclerViewTops() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewJeansList = findViewById(R.id.recyclerViewJeans);
        recyclerViewJeansList.setLayoutManager(linearLayoutManager);

        ArrayList<ClothesList> clotheslist = new ArrayList<>();
        clotheslist.add(new ClothesList("Mist Shorts", "popular_4", 9.75));
        clotheslist.add(new ClothesList("Sasuke Jeans", "popular_5", 8.75));
        clotheslist.add(new ClothesList("Anime Jeans", "popular_7",  9.75));
        clotheslist.add(new ClothesList("Promised Neverland Jeans", "popular_12", 15.0));

        adapter2 = new ClothesAdapter(clotheslist);
        recyclerViewJeansList.setAdapter(adapter2);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            startActivity(new Intent(JeansActivity.this, MainActivity.class));
                            break;

                        case R.id.nav_cart:
                            startActivity(new Intent(JeansActivity.this, CartListActivity.class));
                            break;

                        case R.id.nav_card:
                            selectedFragment = new CouponCardFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
