package com.example.tasktodo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {

Button buttonRegister,alreadyRegister;
  private MyDbHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        db = new MyDbHelper(this);

        EditText editTextNewUsername = findViewById(R.id.editTextNewUsername);
        EditText editTextNewPassword = findViewById(R.id.editTextNewPassword);
        EditText editTextRePassword=findViewById(R.id.editTextRe_Password);
        buttonRegister = findViewById(R.id.buttonRegister);
        alreadyRegister=findViewById(R.id.buttonAlredayRegister);
        buttonRegister.setOnClickListener(v -> {

            String username = editTextNewUsername.getText().toString();
            String password = editTextNewPassword.getText().toString();
            String rePassword=editTextRePassword.getText().toString();
            if(password.equals(rePassword)){
            if (db.insertData(username, password)) {
                db.createTable(username);
                Toast.makeText(Signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish(); // Close the signup activity
            }
            else {
                Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }}
            else {
                Toast.makeText(Signup.this, "Password not match", Toast.LENGTH_SHORT).show();
            }
        });


        alreadyRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });


    }
}