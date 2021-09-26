package com.example.laba1.repository.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.repository.room.dao.FishDAO;

import java.util.List;

public class FishRepository {
    private FishDAO FishDao;
    private LiveData<List<FishDTO>> AllFishes;

    public FishRepository(Application application) {
        FishRoomDatabase db = FishRoomDatabase.getDatabase(application);
        FishDao = db.fishDAO();
        AllFishes = FishDao.getAllFishes();
    }

    public LiveData<List<FishDTO>> getAllFishes() {
        return AllFishes;
    }

    public void insert(FishDTO Fish) {
        FishRoomDatabase.databaseWriteExecutor.execute(() -> {
            FishDao.addFish(Fish);
        });
    }
}
