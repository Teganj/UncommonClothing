package com.teganjennings.uncommonclothing.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teganjennings.uncommonclothing.R;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText regName;
    EditText regEmail;
    EditText regPassword;
    EditText regConfirmPassword;
    Button regSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        regName = findViewById(R.id.name);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regConfirmPassword = findViewById(R.id.confirmPassword);
        regSignupBtn = findViewById(R.id.signupBtn);

        mAuth = FirebaseAuth.getInstance();

        regSignupBtn.setOnClickListener(view -> {
            createUser();
        });
    }

    private void createUser(){
        String name = regName.getText().toString();
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String confirmPassword = regConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            regName.setError("Name cannot be empty");
            regName.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            regEmail.setError("Email cannot be empty");
            regEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            regPassword.setError("Password cannot be empty");
            regPassword.requestFocus();
        //}else if ((password) != (confirmPassword)){
        }else if (TextUtils.isEmpty(confirmPassword)){
            regConfirmPassword.setError("Passwords do not match");
            regConfirmPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(SignUpActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
