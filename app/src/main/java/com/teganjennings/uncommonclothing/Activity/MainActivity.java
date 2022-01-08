package com.teganjennings.uncommonclothing.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teganjennings.uncommonclothing.Fragment.CouponCardFragment;
import com.teganjennings.uncommonclothing.Fragment.ProfileFragment;
import com.teganjennings.uncommonclothing.Adapter.PopularAdapter;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPopular();
        tableNavigation();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ClothesList> clotheslist = new ArrayList<>();
        clotheslist.add(new ClothesList("Haikyuu Top", "popular_1",  9.75));
        clotheslist.add(new ClothesList("Anime Top", "popular_2", 8.75));
        clotheslist.add(new ClothesList("Anime Hoodie", "popular_3", 8.50));
        clotheslist.add(new ClothesList("Mist Shorts", "popular_4", 9.75));
        clotheslist.add(new ClothesList("Sasuke Jeans", "popular_5", 8.75));
        clotheslist.add(new ClothesList("Mist joggers", "popular_6", 8.50));
        clotheslist.add(new ClothesList("Anime Jeans", "popular_7",  9.75));
        clotheslist.add(new ClothesList("Tokyo Ghoul Bottoms", "popular_8", 5.95));
        clotheslist.add(new ClothesList("Kakegurui Bottoms", "popular_9", 8.5));
        clotheslist.add(new ClothesList("Dragon Ball Z Bottoms", "popular_10", 10.99));
        clotheslist.add(new ClothesList("Hunter X Hunter Bottoms", "popular_11", 6.50));
        clotheslist.add(new ClothesList("Promised Neverland Jeans", "popular_12", 15.0));
        clotheslist.add(new ClothesList("Itachi Hoodie", "popular_13", 9.99));
        clotheslist.add(new ClothesList("Re:Zero Joggers", "popular_14", 10.0));


        adapter2 = new PopularAdapter(clotheslist);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void tableNavigation() {
        ConstraintLayout homeBtn = findViewById(R.id.hoodies_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            break;

                        case R.id.nav_cart:
                            startActivity(new Intent(MainActivity.this, CartListActivity.class));
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
