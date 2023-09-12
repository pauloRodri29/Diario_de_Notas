package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Edite_Registre extends AppCompatActivity {
    private List<String> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_registre);
        Button button_voltar = findViewById(R.id.button_voltar), button_salvar = findViewById(R.id.button_salvar), button_excluir = findViewById(R.id.button_excluir);
        EditText search_aluno = findViewById(R.id.search_aluno), editText_N1 = findViewById(R.id.editText_N1), editText_N2 = findViewById(R.id.editText_N2), editText_N3 = findViewById(R.id.editText_N3);
        TextView textView4 = findViewById(R.id.textView4);

        SharedPreferences sharedPreferences = getSharedPreferences("all_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String no_aluno = "Paulo";// search_aluno.getText().toString();

        Map<String, ?> all_dados = sharedPreferences.getAll();

        for (Map.Entry<String, ?> dado : all_dados.entrySet()) {
            String chave = dado.getKey();
            if (chave.equals(no_aluno)) {
                Toast.makeText(Edite_Registre.this, "mostrando: " + no_aluno + " " + no_aluno , Toast.LENGTH_LONG).show();

                String valor = dado.getValue().toString();
                try {
                    JSONObject alunoJson = new JSONObject(valor);
                    int nota1 = alunoJson.getInt("nota_1");
                    int nota2 = alunoJson.getInt("nota_2");
                    int nota3 = alunoJson.getInt("nota_3");

                    editText_N1.setText(nota1);
                    editText_N2.setText(nota2);
                    editText_N3.setText(nota3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(Edite_Registre.this, Tela_de_Registros.class);
                startActivity(voltar);
            }
        });
    }
}