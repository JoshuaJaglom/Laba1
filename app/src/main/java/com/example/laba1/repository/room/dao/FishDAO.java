package com.example.laba1.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.laba1.domain.model.FishDTO;

@Dao
public interface FishDAO {
    @Insert
    void addFish(FishDTO party);

    @Delete
    void deleteFish(FishDTO party);

    @Query("SELECT * FROM Fish_table")
    LiveData<List<FishDTO>> getAllFishes();
}
