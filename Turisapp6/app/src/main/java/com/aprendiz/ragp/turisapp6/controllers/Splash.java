package com.aprendiz.ragp.turisapp6.controllers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aprendiz.ragp.turisapp6.R;
import com.aprendiz.ragp.turisapp6.models.GestorDB;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        inputData();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,MenuT.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,1800);
    }

    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        SQLiteDatabase db = gestorDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LUGARES",null);
        if (!cursor.moveToFirst()){
            try {
                gestorDB.inputHoteles(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                gestorDB.inputRestaurantes(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                gestorDB.inputSitios(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
