package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Tela_Registrar_Notas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_registrar_notas);

        Button voltar_menu = findViewById(R.id.voltar_menu), button_showRegistros = findViewById(R.id.button_showRegistros);
        Button button_registrar = findViewById(R.id.button_registrar);
        EditText nome_aluno = findViewById(R.id.nome_aluno), nota1 = findViewById(R.id.nota1),nota2 = findViewById(R.id.nota2),nota3 = findViewById(R.id.nota3);

        voltar_menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent tela_R = new Intent(Tela_Registrar_Notas.this, MainActivity.class);
                startActivity(tela_R);
            }
        });
        button_showRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaRegistros = new Intent(Tela_Registrar_Notas.this,Tela_de_Registros.class);
                startActivity(telaRegistros);
            }
        });

        button_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("all_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                /* Método ruim
                editor.putFloat(nome + "_nota1", (float) nota1AR);
                editor.putFloat(nome + "_nota2", (float) nota2AR);
                editor.putFloat(nome + "_nota3", (float) nota3AR);*/

                //Método melhorado
                if(nota1.getText().toString().isEmpty() || nota2.getText().toString().isEmpty() || nota3.getText().toString().isEmpty()||nome_aluno.getText().toString().isEmpty()){
                    Toast.makeText(Tela_Registrar_Notas.this, "Erro ao tentar adcionar, por favor preencha todos os Dados",Toast.LENGTH_LONG).show();
                }else {
                    String nome = nome_aluno.getText().toString();
                    double nota1AR = Double.parseDouble(nota1.getText().toString());
                    double nota2AR = Double.parseDouble(nota2.getText().toString());
                    double nota3AR = Double.parseDouble(nota3.getText().toString());

                    JSONObject notas = new JSONObject();
                    try {
                        notas.put("nota1", String.valueOf(nota1AR));
                        notas.put("nota2", String.valueOf(nota2AR));
                        notas.put("nota3", String.valueOf(nota3AR));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Tela_Registrar_Notas.this, "Erro ao tentar adcionar", Toast.LENGTH_LONG).show();
                    }
                editor.putString(nome, notas.toString());
                editor.apply();
                Toast.makeText(Tela_Registrar_Notas.this, "Notas do aluno " + "(" + nome + ")" + " registradas", Toast.LENGTH_LONG).show();
                    // Limpe os campos após o registro
                    nome_aluno.setText("");
                    nota1.setText("");
                    nota2.setText("");
                    nota3.setText("");
                }
            }
        });

        button_showRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Registrar_Notas.this, Tela_de_Registros.class);
                startActivity(intent);
            }
        });
    }
}
