package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewHora = findViewById(R.id.textViewHora);
        Button but_TelaD = findViewById(R.id.but_TelaD);

        Date dataAtual = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String horaFormatada = formatoHora.format(dataAtual);
        textViewHora.setText("Hora Atual: " + horaFormatada);

        but_TelaD.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                Intent tela_R = new Intent(MainActivity.this, Tela_Registrar_Notas.class);
                startActivity(tela_R);
            }
        });

        //Fim
    }
}
