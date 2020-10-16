package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = RegisterActivity.class.getName();
    public static final String Key_RegisterActivity = "Key_RegisterActivity";

    EditText editTextNama, editTextUsername, editTextPassword;
    RadioButton radioButtonLaki, radioButtonPerempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNama = findViewById(R.id.edt_txt_nama);
        editTextUsername = findViewById(R.id.edt_username);
        editTextPassword = findViewById(R.id.edt_password);
        radioButtonLaki = findViewById(R.id.rb_laki);
        radioButtonPerempuan = findViewById(R.id.rb_perempuan);
    }

    public void postSignUp(View view) {
        String password = editTextPassword.getText().toString();
        String username = editTextUsername.getText().toString();
        String nama = editTextNama.getText().toString();
        String jk = radioButtonPerempuan.isChecked() ? "Perempuan" : "Laki-laki";
        Log.d(TAG, "postSignUp: " + password);
        Log.d(TAG, "postSignUp: " + username);
        Log.d(TAG, "postSignUp: " + nama);
        Log.d(TAG, "postSignUp: " + jk);

        Intent intent = new Intent(RegisterActivity.this, RegisterResult.class);
        intent.putExtra(Key_RegisterActivity, new String []{nama, jk, username});
        startActivity(intent);
    }
}