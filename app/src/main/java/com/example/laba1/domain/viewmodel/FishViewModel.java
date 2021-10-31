package com.example.laba1.domain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;
import com.example.laba1.repository.network.Daily;
import com.example.laba1.repository.network.WeatherAnalysis;
import com.example.laba1.repository.room.FishRepository;

import java.util.List;

public class FishViewModel extends AndroidViewModel {

    private FishRepository fishRepository;
    private WeatherAnalysis weatherAnalysis = new WeatherAnalysis();
    private final LiveData<List<FishDTO>> AllFishes;
    private final LiveData<List<UserDTO>> AllUsers;
    private UserDTO user;

    public FishViewModel(@NonNull Application application) {
        super(application);
        fishRepository = new FishRepository(application);
        AllFishes = fishRepository.getAllFishes();
        AllUsers = fishRepository.getAllUsers();
    }

    public void delete(FishDTO fish) {
        fishRepository.delete(fish);
    }

    public LiveData<List<FishDTO>> getAllFishes() {
        return AllFishes;
    }

    public void insertFish(FishDTO fish) {
        fishRepository.insertFish(fish);
    }

    public LiveData<FishDTO> getFishById(int id) {
        return fishRepository.getFishById(id);
    }

    public LiveData<Daily> getWeather() {return weatherAnalysis.getWeather();}

    public LiveData<List<UserDTO>> getAllUsers() {return AllUsers;}

    public void insertUser(UserDTO user) {
        fishRepository.insertUser(user);
    }

    public LiveData<UserDTO> getUserById(int id) {
        return fishRepository.getUserById(id);
    }

    public LiveData<UserDTO> getUserByInf(String login, String password) {
        return fishRepository.getUserByInf(login, password);
    }

    public void changeFish(FishDTO fishDTO) {
        fishRepository.UpdateFish(fishDTO);
    }
}
