// ============================================
// JugadorAdapter.java - Adaptador de Jugadores
// ============================================
package com.example.apiservices;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.JugadorViewHolder> {
    private List<Jugador> jugadores;

    public JugadorAdapter(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public JugadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador, parent, false);
        return new JugadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JugadorViewHolder holder, int position) {
        Jugador jugador = jugadores.get(position);
        holder.tvNombre.setText(jugador.getNombre());
        holder.tvPosicion.setText("Posición: " + jugador.getPosicion());
        holder.tvCamiseta.setText("Camiseta: " + jugador.getNumeroCamiseta());
        holder.tvFechaNacimiento.setText("Nacimiento: " + jugador.getFechaNacimiento());
        holder.tvNacionalidad.setText("Nacionalidad: " + jugador.getNacionalidad());
        holder.tvEquipo.setText("Equipo: " + jugador.getEquipo());
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public static class JugadorViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvPosicion, tvCamiseta, tvFechaNacimiento, tvNacionalidad, tvEquipo;

        public JugadorViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPosicion = itemView.findViewById(R.id.tvPosicion);
            tvCamiseta = itemView.findViewById(R.id.tvCamiseta);
            tvFechaNacimiento = itemView.findViewById(R.id.tvFechaNacimiento);
            tvNacionalidad = itemView.findViewById(R.id.tvNacionalidad);
            tvEquipo = itemView.findViewById(R.id.tvEquipo);
        }
    }
}
