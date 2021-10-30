package com.teganjennings.uncommonclothing.Helper;


import android.content.Context;
import android.widget.Toast;

import com.teganjennings.uncommonclothing.Interface.ChangeNumberItemsListener;
import com.teganjennings.uncommonclothing.List.ClothesList;

import java.util.ArrayList;

public class CartManagement {
    private Context context;
    private TinyDB tinyDB;

    public CartManagement(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(ClothesList item) {
        ArrayList<ClothesList> listFood = getCartList();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<ClothesList> getCartList() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<ClothesList> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public void MinusNumberFood(ArrayList<ClothesList> clotheslist, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (clotheslist.get(position).getNumberInCart() == 1) {
            clotheslist.remove(position);
        } else {
            clotheslist.get(position).setNumberInCart(clotheslist.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList", clotheslist);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<ClothesList> listFood2 = getCartList();
        double fee = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            fee = fee + (listFood2.get(i).getFee() * listFood2.get(i).getNumberInCart());
        }
        return fee;
    }

}

