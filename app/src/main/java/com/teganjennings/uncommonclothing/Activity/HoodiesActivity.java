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
import com.teganjennings.uncommonclothing.Fragment.CartListFragment;
import com.teganjennings.uncommonclothing.Fragment.CouponCardFragment;
import com.teganjennings.uncommonclothing.Fragment.ProfileFragment;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

import java.util.ArrayList;

public class HoodiesActivity extends AppCompatActivity{
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewHoodiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoodies_activity);

        recyclerViewHoodies();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private void recyclerViewHoodies() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHoodiesList = findViewById(R.id.recyclerViewJean);
        recyclerViewHoodiesList.setLayoutManager(linearLayoutManager);

        ArrayList<ClothesList> clotheslist = new ArrayList<>();
        clotheslist.add(new ClothesList("Haikyuu Top", "popular_1", 9.75));
        clotheslist.add(new ClothesList("Anime Top", "popular_2", 8.75));
        clotheslist.add(new ClothesList("Tokyo Ghoul Bottoms", "popular_8", 5.95));
        clotheslist.add(new ClothesList("Hunter X Hunter Bottoms", "popular_11", 6.50));


        adapter2 = new ClothesAdapter(clotheslist);
        recyclerViewHoodiesList.setAdapter(adapter2);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            startActivity(new Intent(HoodiesActivity.this, MainActivity.class));
                            break;

                        case R.id.nav_cart:
                            selectedFragment = new CartListFragment();
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
