package com.example.apiservices;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;

import java.util.*;

public class ListadoJugadoresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    JugadorAdapter adapter;
    List<Jugador> listaJugadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_jugadores);

        recyclerView = findViewById(R.id.recyclerJugadores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int idEquipo = getIntent().getIntExtra("idEquipo", -1);
        obtenerJugadores(idEquipo);
    }

    private void obtenerJugadores(int idEquipo) {
        String url = "https://api.football-data.org/v4/teams/" + idEquipo;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // Obtener nombre del equipo actual
                        String nombreEquipo = response.optString("name", "Equipo desconocido");

                        JSONArray squad = response.getJSONArray("squad");
                        listaJugadores.clear();

                        for (int i = 0; i < squad.length(); i++) {
                            JSONObject jugador = squad.getJSONObject(i);

                            String nombre = jugador.optString("name", "Sin nombre");
                            String posicion = jugador.optString("position", "N/A");
                            String nacionalidad = jugador.optString("nationality", "N/A");
                            String fechaNacimiento = jugador.optString("dateOfBirth", "N/A");
                            int numeroCamiseta = jugador.optInt("shirtNumber", 0);

                            Jugador j = new Jugador(
                                    nombre,
                                    posicion,
                                    nacionalidad,
                                    fechaNacimiento,
                                    numeroCamiseta,
                                    nombreEquipo
                            );

                            listaJugadores.add(j);
                        }

                        adapter = new JugadorAdapter(listaJugadores);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-Auth-Token", "20a2a9ddadbf447287bd1bf95f9ecdbd");
                return headers;
            }
        };

        // Este debe estar dentro del método
        Volley.newRequestQueue(this).add(request);
    }
}
