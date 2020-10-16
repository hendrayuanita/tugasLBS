package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.entity.LogMakanEntity;
import com.example.myapplication.model.MakanModel;

public class Success extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

    }
    public void btnTambahKontak_onClick(View view)
    {
        // Intent
        Intent i = new Intent(Success.this, TambahList.class);
        this.startActivity(i);
    }

    public void btnLihatKontak_onClick(View view)
    {
        // Intent
        Intent i = new Intent(Success.this, LihatSuccess.class);
        this.startActivity(i);
    }

}