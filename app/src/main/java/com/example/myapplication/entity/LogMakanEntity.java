package com.example.myapplication.entity;

import androidx.annotation.NonNull;


public class LogMakanEntity {
    private int id; // Nama kolom id
    private String waktu; // Nama kolom nama
    private String makanan; // Nama kolom nomor_telepon
    public LogMakanEntity()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String nama) {
        this.waktu = waktu;
    }

    public String getMakanan() {
        return makanan;
    }

    public void setMakanan(String makanan) {
        this.makanan = makanan;
    }

}
