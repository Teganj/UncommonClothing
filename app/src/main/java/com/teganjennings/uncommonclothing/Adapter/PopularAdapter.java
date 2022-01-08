package com.teganjennings.uncommonclothing.Adapter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teganjennings.uncommonclothing.Activity.ShowDetailActivity;
import com.teganjennings.uncommonclothing.List.ClothesList;
import com.teganjennings.uncommonclothing.R;

import java.util.ArrayList;


public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<ClothesList> clothesList;

    public PopularAdapter(ArrayList<ClothesList> FoodList) {
        this.clothesList = FoodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(clothesList.get(position).getTitle());
        holder.fee.setText(String.valueOf(clothesList.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(clothesList.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", clothesList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return clothesList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}

