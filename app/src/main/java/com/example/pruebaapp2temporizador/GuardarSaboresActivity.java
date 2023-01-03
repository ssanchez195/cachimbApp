package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        System.out.println(primaryKeySP);

        if(txt_nombreSabor.getText() != null && !preferences.contains(nombre)){
            SharedPreferences.Editor sabor_editor = preferences.edit();
            Bundle bundle = new Bundle();
            bundle.putString(nombre, descripcion);

            sabor_editor.putString("" + primaryKeySP, bundle.toString());
            sabor_editor.commit();

            Toast.makeText(this, "El sabor ha sido guardado", Toast.LENGTH_SHORT).show();
            txt_nombreSabor.setText("");
            txt_descripcionSabor.setText("");
        } else {
            Toast.makeText(this, "El sabor ya existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarSabor(View view){
        SharedPreferences preferences = getSharedPreferences("sabores", Context.MODE_PRIVATE);
        String nombre = txt_nombreSabor.getText().toString();

        if(txt_nombreSabor.getText() != null && preferences.contains(nombre)){
            SharedPreferences.Editor sabor_editor = preferences.edit();
            sabor_editor.remove(nombre);
            sabor_editor.commit();

            Toast.makeText(this, "El sabor se ha eliminado", Toast.LENGTH_SHORT).show();
            txt_nombreSabor.setText("");
            txt_descripcionSabor.setText("");
        } else {
            Toast.makeText(this, "Este sabor no se encuentra guardado", Toast.LENGTH_SHORT).show();
        }
    }
}