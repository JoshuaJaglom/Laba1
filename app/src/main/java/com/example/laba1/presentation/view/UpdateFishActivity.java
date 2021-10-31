package com.example.laba1.presentation.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class UpdateFishActivity extends AppCompatActivity {
    EditText updateFishName, updateFishDescription, updateFishBait, updateFishStartSeason, updateFishEndSeason, updatePreferWeather;
    Button save;
    private LocalDateTime time;
    private FishViewModel fishViewModel;
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent arg = getIntent();
        id = arg.getIntExtra("fishId", 0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_fish);
        updateFishName = findViewById(R.id.ETChangeName);
        updateFishDescription = findViewById(R.id.ETChangeDescription);
        updateFishBait = findViewById(R.id.ETChangeBait);
        updateFishStartSeason = findViewById(R.id.ETChangeStart);
        updateFishEndSeason = findViewById(R.id.ETChangeEnd);
        updatePreferWeather = findViewById(R.id.ETChangeWeather);
        save = findViewById(R.id.button_change);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!updateFishName.getText().toString().isEmpty()) {
                    String name = updateFishName.getText().toString();
                    String description = updateFishDescription.getText().toString();
                    String bait = updateFishBait.getText().toString();
                    String start = updateFishStartSeason.getText().toString();
                    String end = updateFishEndSeason.getText().toString();
                    String weather = updatePreferWeather.getText().toString();

                    fishViewModel = new FishViewModel(getApplication());

                    FishDTO fish = new FishDTO(id, name, description, bait, start, end, weather);
                    fishViewModel.changeFish(fish);
                    finish();
                } else {
                    Toast.makeText(UpdateFishActivity.this,
                            "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });
        updateFishStartSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                time = LocalDateTime.ofInstant(calendar.toInstant(),
                                        calendar.getTimeZone().toZoneId());
                                updateFishStartSeason.setText(time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                            }
                        };

                        new TimePickerDialog(UpdateFishActivity.this, timeSetListener,
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE), true).show();
                    }
                };

                new DatePickerDialog(UpdateFishActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        updateFishEndSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                time = LocalDateTime.ofInstant(calendar.toInstant(),
                                        calendar.getTimeZone().toZoneId());
                                updateFishEndSeason.setText(time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                            }
                        };
                        new TimePickerDialog(UpdateFishActivity.this, timeSetListener,
                                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                                true).show();
                    }
                };
                new DatePickerDialog(UpdateFishActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}