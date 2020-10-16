package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterResult extends AppCompatActivity {

    //Inisialisasi variabel
    TextView tvResultNama, tvResultJenisKelamin, tvResultUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        //Assign view
        tvResultNama = findViewById(R.id.tvResultNama);
        tvResultJenisKelamin = findViewById(R.id.tvResultJenisKelamin);
        tvResultUsername = findViewById(R.id.tvResultUsername);

        //get String array berdasarkan key
        String[] stringArray = getIntent().getStringArrayExtra(RegisterActivity.Key_RegisterActivity);
        tvResultNama.setText(stringArray[0]);
        tvResultJenisKelamin.setText(stringArray[1]);
        tvResultUsername.setText(stringArray[2]);
    }

    public void masuk(View view) {
        Intent i = new Intent(RegisterResult.this, LoginActivity.class);
        startActivity(i);
    }
}