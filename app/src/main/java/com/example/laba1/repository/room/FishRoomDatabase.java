package com.example.laba1.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;
import com.example.laba1.repository.room.dao.FishDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FishDTO.class, UserDTO.class}, version = 1, exportSchema = false)
public abstract class FishRoomDatabase extends RoomDatabase {

    public abstract FishDAO fishDAO();

    private static volatile FishRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FishRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FishRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FishRoomDatabase.class, "Fish_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}