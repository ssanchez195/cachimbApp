package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;


public class RuletaSabores extends AppCompatActivity {

    Integer valorAleatorio;
    Button btn_aleatory;
    TextView txt_sabor;
    EditText txt_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        btn_aleatory = (Button)findViewById(R.id.btn_aleatory);
        txt_sabor = (TextView)findViewById(R.id.txt_nombre);
        txt_descripcion = (EditText)findViewById(R.id.txt_descripcion);

        //Al hacer el mathRandom (id) y recoger el sabor, haremos que los datos se muestren o no al recuperarlos
    }

    public void generateRandom(View view){
        SharedPreferences preferences = getSharedPreferences("sabores", Context.MODE_PRIVATE);

        int primaryKeySP = preferences.getAll().size();
        System.out.println(primaryKeySP);

        valorAleatorio = (int)(primaryKeySP * Math.random());
        System.out.println(valorAleatorio);

        //Con el valor aleatorio tomaremos el valor del maximo idSabor
        if(valorAleatorio != null){

        }
    }

    public void addFlavoursIntent(View view){
        startActivity(new Intent(RuletaSabores.this, GuardarSaboresActivity.class));
    }
}