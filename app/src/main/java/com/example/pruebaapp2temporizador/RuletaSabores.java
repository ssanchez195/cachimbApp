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
import android.widget.Toast;

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

    }


    //Al generar un valor random es como si se borrara tambien, y no entiendo por que ocurre esto
    public void generateRandom(View view){
        SharedPreferences preferences = getSharedPreferences("sabores", Context.MODE_PRIVATE);
        valorAleatorio = (int)((preferences.getAll().size() * Math.random()) + 1);
        String datos = preferences.getString(valorAleatorio.toString(), "");

        if(datos != "" && datos != "="){
            //Este if si tiene valores pero no los recoge
            //Seguramente entre pero te saca de la aplicacion, asi que deberia de darle un repaso
            String[] separador = datos.split("/");
            System.out.println(separador[0].replace("Bundle[{" ,""));
            System.out.println(separador[1].replace("}]", ""));

            txt_sabor.setText(separador[0].replace("Bundle[{" ,""));
            txt_descripcion.setText(separador[1].replace("}]", ""));
        } else {
            startActivity(new Intent(RuletaSabores.this, GuardarSaboresActivity.class));
            Toast.makeText(this, "Debes introducir tus sabores", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFlavoursIntent(View view){
        startActivity(new Intent(RuletaSabores.this, GuardarSaboresActivity.class));
    }
}