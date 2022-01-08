package com.teganjennings.uncommonclothing.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teganjennings.uncommonclothing.Fragment.CouponCardFragment;
import com.teganjennings.uncommonclothing.Fragment.ProfileFragment;
import com.teganjennings.uncommonclothing.Helper.CartManagement;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCardBtn;
    private TextView titleTxt, feeTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private ClothesList object;
    private int numberOrder = 1;
    private CartManagement cartManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        cartManagement = new CartManagement(this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (ClothesList) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getFee());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                cartManagement.insertFood(object);
            }
        });
    }

    private void initView() {
        addToCardBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);
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