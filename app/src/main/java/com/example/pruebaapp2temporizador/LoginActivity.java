package com.example.pruebaapp2temporizador;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button boton_registro;
    Button boton_inicioSesion;
    EditText txt_email;
    EditText txt_pass;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        boton_registro = (Button)findViewById(R.id.btn_registrarse);
        boton_inicioSesion = (Button)findViewById(R.id.btn_iniciarSesion);
        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_pass = (EditText)findViewById(R.id.txt_pass);

        boton_registro.setOnClickListener(view -> {
            String emailUser = txt_email.getText().toString().trim();
            String passUser = txt_pass.getText().toString().trim();

            if(emailUser.isEmpty() || passUser.isEmpty()){
                Toast.makeText(LoginActivity.this, "Complete los datos", Toast.LENGTH_SHORT).show();
            }else {
                createAccount(emailUser, passUser);
            }
        });

        boton_inicioSesion.setOnClickListener(view -> {
            String emailUser = txt_email.getText().toString().trim();
            String passUser = txt_pass.getText().toString().trim();

            if (emailUser.isEmpty() || passUser.isEmpty()){
                Toast.makeText(LoginActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
            }else{
                loginUser(emailUser, passUser);
            }
        });
    }

    private void createAccount(String email, String passUser) {
        mAuth.createUserWithEmailAndPassword(email, passUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(LoginActivity.this, "Bienvenido " + email, Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show());
    }

    private void loginUser(String email, String passUser) {
        mAuth.signInWithEmailAndPassword(email, passUser).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(LoginActivity.this, "Bienvenido " + email, Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show());
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() { }
}