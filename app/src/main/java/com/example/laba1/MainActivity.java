package com.example.laba1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Toast;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;
import com.example.laba1.presentation.view.AddFishActivity;
import com.example.laba1.presentation.view.adapters.FishListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FishViewModel fishViewModel;
    FishListAdapter fishListAdapter = new FishListAdapter();
    private static final int MY_PERMISSIONS_REQUEST_ACCESS = 200;
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
        recyclerView.setAdapter(fishListAdapter);

        fishViewModel = new ViewModelProvider(this).get(FishViewModel.class);
        fishViewModel.getAllFishes().observe(this, fishes -> fishListAdapter.setFishes(fishes));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_ACCESS);
                }
                else if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED){

                    ContentResolver cr = getContentResolver();
                    ContentValues cv = new ContentValues();

                    Calendar startTime = Calendar.getInstance();
                    Calendar endTime = Calendar.getInstance();
                    SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                    try {
                        startTime.setTime(formater.parse(fishListAdapter.getData().get(position).getFishStartSeason()));
                        endTime.setTime(formater.parse(fishListAdapter.getData().get(position).getFishEndSeason()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    cv.put(CalendarContract.Events.TITLE, fishListAdapter.getData().get(position).getFishName());
                    cv.put(CalendarContract.Events.DESCRIPTION, fishListAdapter.getData().get(position).getFishDescription());
                    cv.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
                    cv.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
                    cv.put(CalendarContract.Events.CALENDAR_ID, 1);
                    cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                    cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                    Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    fishListAdapter.rewrite();
                }


            }
        }).attachToRecyclerView(recyclerView);


    }
}