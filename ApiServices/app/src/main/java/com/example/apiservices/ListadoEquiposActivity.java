package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import java.util.*;

public class ListadoEquiposActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TeamsAdapter adapter;
    List<Teams> listaEquipos = new ArrayList<>();
    private static final String TOKEN = "20a2a9ddadbf447287bd1bf95f9ecdbd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_equipos);
        getSupportActionBar().setTitle("Equipos");

        recyclerView = findViewById(R.id.recyclerEquipos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        String idCompetencia = getIntent().getStringExtra("idCompetencia");
        if (idCompetencia != null) {
            cargarEquipos(idCompetencia);
        } else {
            Toast.makeText(this, "Competencia inválida", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void cargarEquipos(String code) {
        String url = "https://api.football-data.org/v4/competitions/" + code + "/teams";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray teamsArray = response.getJSONArray("teams");
                        for (int i = 0; i < teamsArray.length(); i++) {
                            JSONObject obj = teamsArray.getJSONObject(i);
                            int id = obj.getInt("id");
                            String name = obj.getString("name");
                            String tla = obj.optString("tla", "");

                            listaEquipos.add(new Teams(id, name, tla));
                        }
                        adapter = new TeamsAdapter(this, listaEquipos);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al procesar equipos", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Error al cargar equipos", Toast.LENGTH_SHORT).show()
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
