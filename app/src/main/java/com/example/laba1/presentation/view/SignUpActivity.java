package com.example.laba1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;

import java.time.LocalDateTime;

public class SignUpActivity extends AppCompatActivity {

    EditText userName, userLogin, userPassword, confirmPassword;
    Button signUp;
    FishViewModel fishViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName = findViewById(R.id.ETNameUser);
        userLogin = findViewById(R.id.ETLoginUser);
        userPassword = findViewById(R.id.ETPassword);
        confirmPassword = findViewById(R.id.ETConfirmPassword);
        signUp = findViewById(R.id.Sign_Up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userName.getText().toString().isEmpty()) {
                    String name = userName.getText().toString();
                    String login = userLogin.getText().toString();
                    String password = userPassword.getText().toString();
                    String conPassword = confirmPassword.getText().toString();

                    fishViewModel = new FishViewModel(getApplication());
                    UserDTO user = new UserDTO(name, login, password);
                    fishViewModel.insertUser(user);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this,
                            "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}