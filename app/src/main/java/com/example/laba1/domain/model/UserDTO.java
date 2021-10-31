package com.example.laba1.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_table")
public class UserDTO {
    @ColumnInfo(name = "UserId")
    @PrimaryKey(autoGenerate = true)
    private int userId;
    @NonNull
    @ColumnInfo(name = "UserName")
    private String userName;
    @NonNull
    @ColumnInfo(name = "UserLogin")
    private String userLogin;
    @ColumnInfo(name = "UserPassword")
    @Nullable
    private String userPassword;
    @ColumnInfo(name = "UserRole")
    private String userRole = "Moderator";

    public UserDTO(@NonNull String userName, @NonNull String userLogin,
                   @Nullable String userPassword, String userRole) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public UserDTO(@NonNull String userName, @NonNull String userLogin, @Nullable String userPassword) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDTO() {
    }

    @NonNull
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(@NonNull String userLogin) {
        this.userLogin = userLogin;
    }

    @Nullable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@Nullable String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
