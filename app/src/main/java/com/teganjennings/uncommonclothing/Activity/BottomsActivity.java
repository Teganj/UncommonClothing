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
import com.teganjennings.uncommonclothing.Fragment.ProfileFragment;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

import java.util.ArrayList;

public class BottomsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewBottomsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottoms_activity);

        recyclerViewBottoms();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private void recyclerViewBottoms() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBottomsList = findViewById(R.id.recyclerViewBottom);
        recyclerViewBottomsList.setLayoutManager(linearLayoutManager);

        ArrayList<ClothesList> clotheslist = new ArrayList<>();
        clotheslist.add(new ClothesList("Mist Shorts", "popular_4", 9.75));
        clotheslist.add(new ClothesList("Mist joggers", "popular_6", 8.50));
        clotheslist.add(new ClothesList("Tokyo Ghoul Bottoms", "popular_8", 5.95));
        clotheslist.add(new ClothesList("Kakegurui Bottoms", "popular_9", 8.5));
        clotheslist.add(new ClothesList("Dragon Ball Z Bottoms", "popular_10", 10.99));
        clotheslist.add(new ClothesList("Hunter X Hunter Bottoms", "popular_11", 6.50));
        clotheslist.add(new ClothesList("Re:Zero Joggers", "popular_14", 10.0));

        adapter2 = new ClothesAdapter(clotheslist);
        recyclerViewBottomsList.setAdapter(adapter2);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(com.teganjennings.uncommonclothing.Activity.BottomsActivity.this, MainActivity.class));
                            break;

                        case R.id.nav_cart:
                            startActivity(new Intent(com.teganjennings.uncommonclothing.Activity.BottomsActivity.this, CartListActivity.class));
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
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


