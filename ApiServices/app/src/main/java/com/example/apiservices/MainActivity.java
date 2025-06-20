// MainActivity.java
package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    Button btnConectar, btnLimpiar, btnListado;
    TextView jsonText;
    RecyclerView recyclerView;
    CompetitionAdapter adapter;
    List<Competition> lista = new ArrayList<>();
    private static final String TOKEN = "20a2a9ddadbf447287bd1bf95f9ecdbd";
    private static final String URL = "https://api.football-data.org/v4/competitions?plan=TIER_ONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConectar = findViewById(R.id.btnconectar);
        btnLimpiar = findViewById(R.id.btnlimpiar);
        btnListado = findViewById(R.id.listado);
        jsonText = findViewById(R.id.txtjson);
        recyclerView = findViewById(R.id.recyclerCompeticiones);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        btnLimpiar.setOnClickListener(v -> jsonText.setText(""));

        btnConectar.setOnClickListener(v -> requestDatos());

        btnListado.setOnClickListener(v -> {
            if (!lista.isEmpty()) {
                adapter = new CompetitionAdapter(lista, this);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Primero presiona Conectar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestDatos() {
        Log.d("API", "Petición enviada");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                response -> {
                    try {
                        JSONArray comps = response.getJSONArray("competitions");
                        lista.clear();
                        for (int i = 0; i < comps.length(); i++) {
                            JSONObject c = comps.getJSONObject(i);

                            // Extraer datos del JSON
                            String id = String.valueOf(c.optInt("id", 0)); // ID como String
                            String code = c.optString("code", "");
                            String name = c.optString("name", "");
                            String area = "";

                            // "area" es un objeto JSON
                            if (c.has("area")) {
                                JSONObject areaObj = c.getJSONObject("area");
                                area = areaObj.optString("name", "");
                            }

                            // Crear objeto Competition con todos los datos
                            lista.add(new Competition(id, area, code, name));
                        }

                        jsonText.setText("Competiciones cargadas: " + lista.size());

                    } catch (JSONException e) {
                        jsonText.setText("Error al procesar JSON");
                    }
                },
                error -> jsonText.setText("Error de red: " + error.getMessage())
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", TOKEN);
                return headers;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

}
