package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class GuardarSaboresActivity extends AppCompatActivity {

    EditText txt_nombreSabor;
    EditText txt_descripcionSabor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_sabores);

        txt_nombreSabor = (EditText) findViewById(R.id.txt_nombreSabor);
        txt_descripcionSabor = (EditText)findViewById(R.id.txt_descripcionSabor);
    }

    public void agregarSabor(View view){
        SharedPreferences preferences = getSharedPreferences("sabores", Context.MODE_PRIVATE);
        String nombre = txt_nombreSabor.getText().toString();
        String descripcion = txt_descripcionSabor.getText().toString();

        int primaryKeySP = preferences.getAll().size() + 1;

        if(txt_nombreSabor.getText() != null && !preferences.contains(nombre)){
            SharedPreferences.Editor sabor_editor = preferences.edit();
            String datos = nombre + "/" + descripcion;

            sabor_editor.putString("" + primaryKeySP, datos);
            sabor_editor.apply();

            Toast.makeText(this, "El sabor ha sido guardado", Toast.LENGTH_SHORT).show();
            txt_nombreSabor.setText("");
            txt_descripcionSabor.setText("");
        } else {
            Toast.makeText(this, "El sabor ya existe", Toast.LENGTH_SHORT).show();
        }
    }

    //Recuerda que los sabores estan guardados como WTF/Descripcion
    public void eliminarSabor(View view){
        SharedPreferences preferences = getSharedPreferences("sabores", Context.MODE_PRIVATE);
        String nombre = txt_nombreSabor.getText().toString();
        Map<String, ?> allEntries = preferences.getAll();

        for (Map.Entry<String, ?> entrada : allEntries.entrySet()) {
            SharedPreferences.Editor editor = preferences.edit();
            String clave = entrada.getKey();
            String nomYDesc = entrada.getValue().toString();

            if (nomYDesc.contains(nombre)  && nombre.length() >= 3) {
                System.out.println("Se ha borrado el sabor " + nomYDesc);
                editor.remove(clave);
                editor.apply();
                break;
            }
        }
    }
}