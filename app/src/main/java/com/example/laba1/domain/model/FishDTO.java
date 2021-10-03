package com.example.laba1.domain.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fish_table")
public class FishDTO {
    @ColumnInfo(name = "fishId")
    @PrimaryKey(autoGenerate = true)
    private int fishId;
    @NonNull
    @ColumnInfo(name = "fishName")
    private String fishName;
    @ColumnInfo(name = "fishDescription")
    private String fishDescription;
    @ColumnInfo(name = "fishBait")
    private String fishBait;
    @ColumnInfo(name = "FishStartSeason")
    private String fishStartSeason;
    @ColumnInfo(name = "FishEndSeason")
    private String fishEndSeason;

    public FishDTO() {
    }

    public void setFishStartSeason(String fishStartSeason) {
        this.fishStartSeason = fishStartSeason;
    }

    public void setFishEndSeason(String fishEndSeason) {
        this.fishEndSeason = fishEndSeason;
    }

    public String getFishStartSeason() {
        return fishStartSeason;
    }

    public String getFishEndSeason() {
        return fishEndSeason;
    }

    public String getFishBait() {
        return fishBait;
    }

    public void setFishBait(String fishBait) {
        this.fishBait = fishBait;
    }

    public int getFishId() {
        return fishId;
    }

    public FishDTO(@NonNull String fishName, String fishDescription, String fishBait, String fishStartSeason, String fishEndSeason) {
        this.fishName = fishName;
        this.fishDescription = fishDescription;
        this.fishBait = fishBait;
        this.fishStartSeason = fishStartSeason;
        this.fishEndSeason = fishEndSeason;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public String getFishDescription() {
        return fishDescription;
    }

    public void setFishDescription(String fishDescription) {
        this.fishDescription = fishDescription;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
}
