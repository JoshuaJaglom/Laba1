package com.example.laba1.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;

@Dao
public interface FishDAO {
    @Insert
    void addFish(FishDTO fish);

    @Delete
    void deleteFish(FishDTO fish);

    @Query("SELECT * FROM Fish_table")
    LiveData<List<FishDTO>> getAllFishes();

    @Query("SELECT * FROM Fish_table WHERE fishId = :id")
    LiveData<FishDTO> getFishById(int id);

    @Insert
    void addUser(UserDTO user);

    @Delete
    void deleteUser(UserDTO user);

    @Query("SELECT * FROM User_table")
    LiveData<List<UserDTO>> getAllUsers();

    @Query("SELECT * FROM User_table WHERE UserId = :id")
    LiveData<UserDTO> getUserById(int id);

    @Query("SELECT * FROM User_table Where userLogin =:login AND userPassword = :password")
    LiveData<UserDTO> getUserByInf(String login, String password);

    @Update
    void changeFish(FishDTO fish);

}
