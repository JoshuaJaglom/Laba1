package com.example.laba1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;


public class AddFishActivity extends AppCompatActivity {
    EditText FishName, FishDescription, FishBait;
    Button Save;
    private FishViewModel fishViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fish);

        FishName = findViewById(R.id.ETName);
        FishDescription = findViewById(R.id.ETDescription);
        FishBait = findViewById(R.id.ETBait);
        Save = findViewById(R.id.button_save);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FishName.getText().toString().isEmpty()) {
                    String Name = FishName.getText().toString();
                    String Description = FishDescription.getText().toString();
                    String Bait = FishBait.getText().toString();

                    fishViewModel = new FishViewModel(getApplication());
                    FishDTO fish = new FishDTO(Name, Description, Bait);
                    fishViewModel.insert(fish);
                    finish();
                }else {
                    Toast.makeText(AddFishActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
