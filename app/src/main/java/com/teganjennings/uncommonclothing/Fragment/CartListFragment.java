package com.teganjennings.uncommonclothing.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teganjennings.uncommonclothing.Adapter.CartAdapter;
import com.teganjennings.uncommonclothing.Helper.CartManagement;
import com.teganjennings.uncommonclothing.Interface.ChangeNumberItemsListener;
import com.teganjennings.uncommonclothing.R;

public class CartListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cartlist, container, false);
        view.setBackgroundColor(Color.WHITE);

        return inflater.inflate(R.layout.fragment_cartlist, container, false);
    }

public class CartListActivity1 extends AppCompatActivity{


    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private CartManagement cartManagement;
    private TextView totalFeeTxt, deliveryTxt, totalTxt, emptyTxt;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        cartManagement = new CartManagement(this);

        initView();
        initList();
        calculateCart();
    }


    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(cartManagement.getCartList(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (cartManagement.getCartList().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 5;

        double total = Math.round((cartManagement.getTotalFee() + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(cartManagement.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("€" + itemTotal);
        deliveryTxt.setText("€" + delivery);
        totalTxt.setText("€" + total);
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.nav_cart:
                            selectedFragment = new ProfileFragment();
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
}