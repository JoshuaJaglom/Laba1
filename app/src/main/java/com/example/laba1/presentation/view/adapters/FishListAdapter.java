package com.example.laba1.presentation.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;


import java.util.ArrayList;
import java.util.List;

public class FishListAdapter extends RecyclerView.Adapter<FishListAdapter.FishListHolder> {
    private List<FishDTO> fishes = new ArrayList<>();

    @NonNull
    @Override
    public FishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FishListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FishListHolder holder, int position) {
        FishDTO currentFish = fishes.get(position);
        holder.name.setText(currentFish.getFishName());
        holder.description.setText(currentFish.getFishDescription());
        holder.bait.setText(currentFish.getFishBait());
    }

    @Override
    public int getItemCount() {
        return fishes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setOrders(List<FishDTO> fishes){
        this.fishes = fishes;
        notifyDataSetChanged();
    }

    public void rewrite(){
        notifyDataSetChanged();
    }

    public List<FishDTO> getData() {
        return fishes;
    }

    class FishListHolder extends RecyclerView.ViewHolder{
        private TextView name, description, bait;

        public FishListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            description = itemView.findViewById(R.id.tvOpisanie);
            bait = itemView.findViewById(R.id.tvNazhivka);

        }
    }
}
