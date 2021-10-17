package com.example.laba1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;
import com.example.laba1.repository.network.Daily;
import com.example.laba1.repository.network.Temp;

public class InfoActivity extends AppCompatActivity {
    FishViewModel fishViewModel;
    Integer id;


    private TextView name, description, bait, start, end, predPogoda, ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.tvName);
        description = findViewById(R.id.tvOpisanie);
        bait = findViewById(R.id.tvNazhivka);
        start = findViewById(R.id.tvStart);
        end = findViewById(R.id.tvEnd);
        predPogoda = findViewById(R.id.tvPredPogoda);
        ver = findViewById(R.id.tvVer);

        Intent arg = getIntent();
        id = arg.getIntExtra("fishId", 0);
        fishViewModel = new FishViewModel(getApplication());
        fishViewModel.getById(id).observe(this, new Observer<FishDTO>() {
            @Override
            public void onChanged(FishDTO fishDTO) {
                name.setText(fishDTO.getFishName());
                description.setText(fishDTO.getFishDescription());
                bait.setText(fishDTO.getFishBait());
                start.setText(fishDTO.getFishStartSeason());
                end.setText(fishDTO.getFishEndSeason());
                predPogoda.setText(fishDTO.getPreferredWeather());
                fishViewModel.getWeather().observe(InfoActivity.this, new Observer<Daily>() {
                    @Override
                    public void onChanged(Daily daily) {
                        double pop = daily.getPop();
                        Temp temp = daily.getTemp();
                        double cov = pop * temp.getDay();
                        if (cov == Double.parseDouble(fishDTO.getPreferredWeather())) {
                            ver.setText("Высокая");
                        } else if (cov > Double.parseDouble(fishDTO.getPreferredWeather())){
                            ver.setText("Средняя");
                        } else{
                            ver.setText("Маленькая");
                        }
                    }
                });
            }
        });
    }
}