package com.example.laba1.domain.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fish_table")
public class FishDTO {
    @ColumnInfo(name = "FishId")
    @PrimaryKey(autoGenerate = true)
    private int FishId;
    @NonNull
    @ColumnInfo(name = "FishName")
    private String FishName;
    @ColumnInfo(name = "FishDescription")
    private String FishDescription;
    @ColumnInfo(name = "FishBait")
    private String FishBait;

    public String getFishBait() {
        return FishBait;
    }

    public void setFishBait(String fishBait) {
        FishBait = fishBait;
    }

    public int getFishId() {
        return FishId;
    }

    public void setFishId(int fishId) {
        FishId = fishId;
    }

    public FishDTO(@NonNull String fishName) {
        FishName = fishName;
    }

    public String getFishName() {
        return FishName;
    }

    public String getFishDescription() {
        return FishDescription;
    }

    public void setFishDescription(String fishDescription) {
        FishDescription = fishDescription;
    }

    public void setFishName(String FishName) {
        FishName = FishName;
    }
}
