package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tela_de_Registros extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_registros);

        Button voltar_tela_reg = findViewById(R.id.voltar_tela_reg), button_excluir_ALL = findViewById(R.id.button_excluir_ALL);
        Button button_edit_registros = findViewById(R.id.button_edit_registros);
        ListView Lista_Registros_View = findViewById(R.id.Lista_Registros_View);

        // Recuperar todos os dados do SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("all_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Map<String, ?> allEntries = sharedPreferences.getAll();

        registros = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue().toString();

            // Converter o valor JSON de volta para uma representação legível
            Gson gson = new Gson();
            Type listType = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> dados = gson.fromJson(valor, listType);

            // Construir uma representação de string dos dados
            int n = 1;
            String registro = "Aluno: " + chave + "\n";
            for (Map.Entry<String, String> item : dados.entrySet()) {
                registro += "Nota "+ n + ": " + item.getValue() + "\n";
                n++;
            }

            registros.add(registro);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, registros);
        Lista_Registros_View.setAdapter(adapter);

        voltar_tela_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_R = new Intent(Tela_de_Registros.this, Tela_Registrar_Notas.class);
                startActivity(tela_R);
            }
        });
        button_excluir_ALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
            }
        });
        button_edit_registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_edit = new Intent(Tela_de_Registros.this, Edite_Registre.class);
                startActivity(tela_edit);
            }
        });
    }
}
