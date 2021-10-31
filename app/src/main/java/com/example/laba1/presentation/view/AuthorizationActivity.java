package com.example.laba1.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.laba1.MainActivity;
import com.example.laba1.R;
import com.example.laba1.domain.model.FishDTO;
import com.example.laba1.domain.model.UserDTO;
import com.example.laba1.domain.viewmodel.FishViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class AuthorizationActivity extends AppCompatActivity {
    EditText userLogin, userPassword;
    TextView signUp;
    Button signIn;
    ImageButton SignInGoogle;
    private FishViewModel fishViewModel;
    int RC_SIGN_IN = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        userLogin = findViewById(R.id.ETLogin);
        userPassword = findViewById(R.id.ETPassword);
        signIn = findViewById(R.id.button_signIn);
        fishViewModel = new FishViewModel(getApplication());
        SignInGoogle = findViewById(R.id.GoogleImageButton);
        signUp = findViewById(R.id.ET_sign_up);


        signIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userLogin.getText().toString().isEmpty()) {
                    String login = userLogin.getText().toString();
                    String password = userPassword.getText().toString();
                    fishViewModel.getUserByInf(login, password).observe(AuthorizationActivity.this, (UserDTO userDTO) -> {
                        if (userDTO != null) {
                            Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                            intent.putExtra("role", userDTO.getUserRole());
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AuthorizationActivity.this, "Пароль или логин неправилен",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });
                }
            }
        });

        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthorizationActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInGoogle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (ApiException e) {

        }
    }
}