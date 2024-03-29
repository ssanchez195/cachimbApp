package com.example.pruebaapp2temporizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CronometroActivity extends AppCompatActivity {

    boolean Started;
    CountDownTimer cuenta;
    Spinner spinnerMinu;
    Button btn_volver;
    Button btn_start;
    TextView toShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        btn_start = (Button)findViewById(R.id.buttonPlay);

        Started = false;
        spinnerMinu = (Spinner)findViewById(R.id.spinnerMinutos);
        toShow = (TextView)findViewById(R.id.txt_Crono);
        btn_volver = (Button)findViewById(R.id.btn_volver);

        String options[] = new String[6];
        options[0] = ("Desplegar opciones");
        options[1] = ("Kaloud sin precalentar");
        options[2] = ("Kaloud precalentado");
        options[3] = ("Provost");
        options[4] = ("Rejilla");
        options[5] = ("Carbones solos");

        ArrayAdapter <String> valores = new ArrayAdapter<>(this, R.layout.spinner_item_cronometroopciones, options);
        spinnerMinu.setAdapter(valores);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CronometroActivity.this, Utilidades.class));
            }
        });
    }

    public void buttonPlay(View vista) {
        String selection = spinnerMinu.getSelectedItem().toString();
        int minute;
        if(selection.equals("Desplegar opciones"))
            minute = 300000;
        else if(selection.equals("Kaloud sin precalentar"))
            minute = 300000;
        else if(selection.equals("Kaloud precalentado"))
            minute = 210000;
        else if(selection.equals("Provost"))
            minute = 300000;
         else if(selection.equals("Rejilla"))
            minute = 360000;
        else if(selection.equals("Carbones solos"))
            minute = 360000;
        else
            minute = 300000;

        if (!Started) {
            //The "If" hace que se restablezca si el CountDownTimer está funcionando
            final long valor = minute;
            cuenta = new CountDownTimer(valor, 1000) {
                @Override
                public void onTick(long i) {
                    if (Started) {
                        long time = i / 1000;
                        int minute = (int) (time / 60);
                        long seconds = time % 60;
                        String txt_minute = String.format("%02d", minute);
                        String txt_seconds = String.format("%02d", seconds);
                        toShow.setText("" + txt_minute + " : " + txt_seconds);
                    }
                }
                @Override
                public void onFinish() {
                    if (Started) {
                        MediaPlayer mp = MediaPlayer.create(CronometroActivity.this, R.raw.ninocantando);
                        mp.start();
                    }
                }
            }.start();

            btn_start.setText("Reiniciar");
            Started = true;

        } else {
            Toast.makeText(this, "Contador reiniciado", Toast.LENGTH_SHORT).show();
            btn_start.setText("Iniciar");
            Started = false;
            toShow.setText("00:00");
            cuenta.cancel();
        }
    }
}
