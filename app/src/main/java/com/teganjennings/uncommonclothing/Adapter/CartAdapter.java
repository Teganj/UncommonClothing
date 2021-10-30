package com.teganjennings.uncommonclothing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teganjennings.uncommonclothing.Helper.CartManagement;
import com.teganjennings.uncommonclothing.Interface.ChangeNumberItemsListener;
import com.teganjennings.uncommonclothing.R;
import com.teganjennings.uncommonclothing.List.ClothesList;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<ClothesList> clotheslist;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private CartManagement cartManagement;



    public CartAdapter(ArrayList<ClothesList> ClothesList, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.clotheslist = ClothesList;
        this.changeNumberItemsListener = changeNumberItemsListener;
        cartManagement = new CartManagement(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(clotheslist.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(clotheslist.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((clotheslist.get(position).getNumberInCart() * clotheslist.get(position).getFee()) * 100.0) / 100.0));
        holder.num.setText(String.valueOf(clotheslist.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(clotheslist.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartManagement.plusNumberFood(clotheslist, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartManagement.MinusNumberFood(clotheslist, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return clotheslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCard);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
