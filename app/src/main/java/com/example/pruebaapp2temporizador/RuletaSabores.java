package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class RuletaSabores extends AppCompatActivity {

    Button btn_aleatory;
    TextView txt_sabor;
    EditText txt_descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);

        btn_aleatory = (Button)findViewById(R.id.btn_aleatory);
        txt_sabor = (TextView)findViewById(R.id.txt_nombreSabor);
        txt_descripcion = (EditText)findViewById(R.id.txt_descripcion);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Poner el path filtrador
        DatabaseReference myRef = database.getReference("usuarios");

        //Al hacer el mathRandom (id) y recoger el sabor, haremos que los datos se muestren o no al recuperarlos

        // Obtenemos el ID del usuario
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();


        // En la referencia iria el id del sabor que obtendriamos con el math.random
        // Obtenemos la referencia al nodo en la ruta /users/$user_id/data/
        DatabaseReference nodeReference = database.getReference("users").child(userId).child("data").child("some_node_id");

        // Leemos dicho nodo
        nodeReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recuperar el nombre del sabor, y la descripcion si el usuario la ha introducido
                String nombreSabor = dataSnapshot.child("nombreSabor").getValue(String.class);
                String descripcion = dataSnapshot.child("descripcion").getValue(String.class);

                txt_sabor.setVisibility(View.VISIBLE);
                txt_sabor.setText(nombreSabor);
                txt_descripcion.setVisibility(View.VISIBLE);
                txt_descripcion.setText(descripcion);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                txt_sabor.setVisibility(View.INVISIBLE);
                txt_descripcion.setVisibility(View.INVISIBLE);
            }
        });
    }
}