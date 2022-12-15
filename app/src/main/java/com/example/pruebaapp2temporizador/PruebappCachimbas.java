package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PruebappCachimbas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebapp_cachimbas);
    }

    public void Sabores(View vista){
        Toast.makeText(this, "Taboo=Muerte", Toast.LENGTH_SHORT).show();
    }
    public void Cazoletas(View vista){
        Toast.makeText(this, "Fiel al barro blanco", Toast.LENGTH_SHORT).show();
    }
    public void Cronometro(View vista){
        Intent SiguienteCronometro = new Intent(this, PruebappCronometro.class);
        startActivity(SiguienteCronometro);
    }
}