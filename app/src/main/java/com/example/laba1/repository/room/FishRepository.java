package com.example.laba1.repository.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;
import com.example.laba1.repository.room.dao.FishDAO;

import java.util.List;

public class FishRepository {
    private FishDAO FishDao;
    private LiveData<List<FishDTO>> AllFishes;
    private LiveData<FishDTO> mDataFish;
    private LiveData<List<UserDTO>> AllUsers;
    private LiveData<UserDTO> mDataUser;
    private LiveData<UserDTO> mLoginUser;
    private LiveData<UserDTO> user;

    public FishRepository(Application application) {
        FishRoomDatabase db = FishRoomDatabase.getDatabase(application);
        FishDao = db.fishDAO();
        AllFishes = FishDao.getAllFishes();
        AllUsers = FishDao.getAllUsers();
    }

    public void delete(FishDTO fish) {
        FishRoomDatabase.databaseWriteExecutor.execute(() -> {
            FishDao.deleteFish(fish);
        });
    }

    public LiveData<List<FishDTO>> getAllFishes() {
        return AllFishes;
    }

    public void insertFish(FishDTO Fish) {
        FishRoomDatabase.databaseWriteExecutor.execute(() -> {
            FishDao.addFish(Fish);
        });
    }

    public LiveData<FishDTO> getFishById(int id) {
        mDataFish = FishDao.getFishById(id);
        return mDataFish;
    }

    public LiveData<List<UserDTO>> getAllUsers() {
        return AllUsers;
    }

    public void insertUser(UserDTO user) {
        FishRoomDatabase.databaseWriteExecutor.execute(() -> {
            FishDao.addUser(user);
        });
    }

    public LiveData<UserDTO> getUserById(int id) {
        mDataUser = FishDao.getUserById(id);
        return mDataUser;
    }

    public LiveData<UserDTO> getUserByInf(String login, String password) {
        user = FishDao.getUserByInf(login, password);
        return user;
    }

    public void UpdateFish(FishDTO fish) {
        FishRoomDatabase.databaseWriteExecutor.execute(() -> {
            FishDao.changeFish(fish);
        });
    }

}
