package com.example.laba1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;
import com.example.laba1.presentation.view.AddFishActivity;
import com.example.laba1.presentation.view.adapters.FishListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FishViewModel fishViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFishActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FishListAdapter fishListAdapter = new FishListAdapter();
        recyclerView.setAdapter(fishListAdapter);

        fishViewModel = new ViewModelProvider(this).get(FishViewModel.class);
        fishViewModel.getAllFishes().observe(this, new Observer<List<FishDTO>>() {
            @Override
            public void onChanged(List<FishDTO> fishes) {
                fishListAdapter.setOrders(fishes);
            }
        });
    }
}