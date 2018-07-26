package com.aprendiz.ragp.turisapp6.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp6.R;
import com.aprendiz.ragp.turisapp6.models.Lugares;

public class Detalle extends AppCompatActivity {
    TextView txtNombreD;
    TextView txtDescripcionD;
    ImageView imgDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        inizialite();
        inputData();
    }


    private void inizialite() {
        txtNombreD = findViewById(R.id.txtNombreD);
        txtDescripcionD = findViewById(R.id.txtDescripcionD);
        imgDetalle = findViewById(R.id.imgDetalle);
    }

    private void inputData() {
        Lugares lugares = MenuT.lugares;
        txtNombreD.setText(lugares.getNombre());
        txtDescripcionD.setText(lugares.getDescripcion());
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize=2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),lugares.getImagen(),op);
        imgDetalle.setImageBitmap(bitmap);
    }
}
