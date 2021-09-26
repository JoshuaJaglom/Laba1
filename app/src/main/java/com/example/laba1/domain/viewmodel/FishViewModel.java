package com.example.laba1.domain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.repository.room.FishRepository;

import java.util.List;

public class FishViewModel extends AndroidViewModel {

    private FishRepository fishRepository;

    private final LiveData<List<FishDTO>> AllFishes;

    public FishViewModel(@NonNull Application application) {
        super(application);
        fishRepository = new FishRepository(application);
        AllFishes = fishRepository.getAllFishes();
    }

    LiveData<List<FishDTO>> getAllFishes() {
        return AllFishes;
    }

    public void insert(FishDTO fish) {
        fishRepository.insert(fish);
    }
}
