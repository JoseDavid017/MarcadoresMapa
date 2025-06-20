package com.example.apiservices;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private Context context;
    private List<Teams> lista;

    public TeamsAdapter(Context context, List<Teams> lista) {
        this.context = context;
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreEquipo, tvTla;

        public ViewHolder(View view) {
            super(view);
            tvNombreEquipo = view.findViewById(R.id.tvNombreEquipo);
            tvTla = view.findViewById(R.id.tvTla);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_equipos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Teams team = lista.get(position);

        holder.tvNombreEquipo.setText(team.getNombre());
        holder.tvTla.setText(team.getTla());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListadoJugadoresActivity.class);
            intent.putExtra("idEquipo", team.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
