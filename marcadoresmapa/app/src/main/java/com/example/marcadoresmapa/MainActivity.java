package com.example.marcadoresmapa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cargar el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Coordenadas
        LatLng codazzi = new LatLng(9.9747, -73.2615);
        LatLng barranquilla = new LatLng(10.9639, -74.7964);
        LatLng medellin = new LatLng(6.2442, -75.5812);

        // Agregar marcadores
        mMap.addMarker(new MarkerOptions().position(codazzi).title("Agustín Codazzi, Cesar"));
        mMap.addMarker(new MarkerOptions().position(barranquilla).title("Barranquilla, Atlántico"));
        mMap.addMarker(new MarkerOptions().position(medellin).title("Medellín, Antioquia"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(codazzi, 5.5f));
    }
}
