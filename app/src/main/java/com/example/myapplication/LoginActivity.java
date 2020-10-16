package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    // Deklarasi variable editTextEmail dengan tipe EditText
    EditText editTextEmail;
    // Deklarasi variabel editTextPassword dengan tipe EditText
    EditText editTextPassword;

    // SharedPreferences yang akan digunakan untuk menulis dan membaca data
    private SharedPreferences sharedPrefs;


    // Key-key untuk data yang disimpan di SharedPrefernces
    private static final String USERNAME_KEY = "key_username";
    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    private static final String DUMMY_USERNAME = "dtsawardee";
    private static final String DUMMY_PASSWORD = "dtsrocks";
    private CheckBox chkRememberUsername;
    private CheckBox chkKeepLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Inisialisasi SharedPreferences
        this.sharedPrefs = this.getSharedPreferences("dtsapp_sharedprefs", Context.MODE_PRIVATE);
        this.initComponents();

        this.autoLogin();
        this.loadSavedUsername();
    }
    private boolean validateCredential()
    {
        String currentUsername = this.editTextEmail.getText().toString();
        String currentPassword = this.editTextPassword.getText().toString();

        return (Objects.equals(currentUsername, DUMMY_USERNAME)
                && Objects.equals(currentPassword, DUMMY_PASSWORD));
    }
    private void initComponents() {
        // Init components
        this.editTextEmail = this.findViewById(R.id.edt_username);
        this.editTextPassword = this.findViewById(R.id.edt_password);
        this.chkRememberUsername = this.findViewById(R.id.chk_remember_username);
        this.chkKeepLogin = this.findViewById(R.id.chk_keep_login);
    }

    private void autoLogin() {
        // Cek apakah sebelumnya aplikasi diatur agar bypass login?
        // Jika ya maka langsung buka activity berikutnya
        boolean keepLogin = this.sharedPrefs.getBoolean(KEEP_LOGIN_KEY, false);

        if(keepLogin)
        {
            Intent i = new Intent(LoginActivity.this, Success.class);
            startActivity(i);
        }
        else
        {

        }
    }
    private void saveUsername()
    {
        // Menyimpan username bila diperlukan

        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if(this.chkRememberUsername.isChecked())
            editor.putString(USERNAME_KEY, this.editTextEmail.getText().toString());
        else
            editor.remove(USERNAME_KEY);

        editor.apply();
    }
    private void loadSavedUsername()
    {
        // Memeriksa apakah sebelumnya ada username yang tersimpan?
        // Jika ya, maka tampilkan username tersebut di EditText username.

        String savedUsername = this.sharedPrefs.getString(USERNAME_KEY, null);

        if(savedUsername != null)
        {
            this.editTextEmail.setText(savedUsername);

            this.chkRememberUsername.setChecked(true);
        }
    }
    public void onBtnLogin_Click(View view)
    {
        boolean valid = this.validateCredential();

        if(valid)
        {
            Intent i = new Intent(LoginActivity.this, Success.class);
            startActivity(i);

            this.saveUsername();
            this.makeAutoLogin();
        }
        else
        {
            Toast.makeText(this, "Invalid username and/or password!", Toast.LENGTH_LONG).show();
        }
    }

    private void makeAutoLogin() {
        // Mengatur agar selanjutnya pada saat aplikasi dibuka menjadi otomatis login
        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if(this.chkKeepLogin.isChecked())
            editor.putBoolean(KEEP_LOGIN_KEY, true);
        else
            editor.remove(KEEP_LOGIN_KEY);

        editor.apply();
    }

    public void postLogin(View view) {
        // Validasi input email dan password kosong
        if(TextUtils.isEmpty(editTextEmail.getText().toString().trim()) &&
                TextUtils.isEmpty(editTextPassword.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Email dan Password tidak boleh kosong!",
                    Toast.LENGTH_LONG).show();
        }
        // Validasi input email kosong
        else if(TextUtils.isEmpty(editTextEmail.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Email tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
        // Validasi inputan tipe email
        else if(!isValidEmail(editTextEmail.getText().toString().trim())) {
            Toast.makeText(view.getContext(), "Email tidak valid!", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(editTextPassword.getText().toString())) {
            Toast.makeText(view.getContext(), "Password tidak boleh kosong!", Toast.LENGTH_LONG).show();
        }
    else {
            Intent i = new Intent(LoginActivity.this, Success.class);
            startActivity(i);
        }
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public void clickSignUp(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}