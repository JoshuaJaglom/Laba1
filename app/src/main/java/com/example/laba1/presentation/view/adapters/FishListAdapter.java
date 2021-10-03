package com.example.laba1.presentation.view.adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba1.MainActivity;
import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        holder.start.setText(currentFish.getFishStartSeason());
        holder.end.setText(currentFish.getFishEndSeason());
    }

    @Override
    public int getItemCount() {
        return fishes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFishes(List<FishDTO> fishes){
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
        private TextView name, description, bait, start, end;
        public FishListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            description = itemView.findViewById(R.id.tvOpisanie);
            bait = itemView.findViewById(R.id.tvNazhivka);
            start = itemView.findViewById(R.id.tvStart);
            end = itemView.findViewById(R.id.tvEnd);
        }
    }
}
