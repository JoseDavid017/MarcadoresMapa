package com.example.apiservices;

import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.ViewHolder> {
    private List<Competition> competitionList;
    private Context context;

    public CompetitionAdapter(List<Competition> competitionList, Context context) {
        this.competitionList = competitionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_competition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Competition comp = competitionList.get(position);
        holder.txtId.setText("ID: " + comp.getId());
        holder.txtArea.setText("Área: " + comp.getArea());
        holder.txtCode.setText("Código: " + comp.getCode());
        holder.txtName.setText("Nombre: " + comp.getName());
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtArea, txtCode, txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtArea = itemView.findViewById(R.id.txtArea);
            txtCode = itemView.findViewById(R.id.txtCode);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
