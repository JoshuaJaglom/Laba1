package com.example.laba1.domain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.repository.network.Daily;
import com.example.laba1.repository.network.WeatherAnalysis;
import com.example.laba1.repository.room.FishRepository;

import java.util.List;

public class FishViewModel extends AndroidViewModel {

    private FishRepository fishRepository;
    private WeatherAnalysis weatherAnalysis = new WeatherAnalysis();
    private final LiveData<List<FishDTO>> AllFishes;

    public FishViewModel(@NonNull Application application) {
        super(application);
        fishRepository = new FishRepository(application);
        AllFishes = fishRepository.getAllFishes();
    }

    public LiveData<List<FishDTO>> getAllFishes() {
        return AllFishes;
    }

    public void insert(FishDTO fish) {
        fishRepository.insert(fish);
    }

    public LiveData<FishDTO> getById(int id) {
        return fishRepository.getById(id);
    }

    public LiveData<Daily> getWeather() {return weatherAnalysis.getWeather();}
}
