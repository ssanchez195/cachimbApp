package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Utilidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils);
    }

    public void Sabores(View vista){
        //Hacer el foro?
        Toast.makeText(this, "Taboo=Muerte", Toast.LENGTH_SHORT).show();
    }

    public void Ruleta(View vista){
        Intent ruleta = new Intent(this, RuletaSabores.class);
        startActivity(ruleta);
    }

    public void Cronometro(View vista){
        Intent cronometro = new Intent(this, CronometroActivity.class);
        startActivity(cronometro);
    }
}