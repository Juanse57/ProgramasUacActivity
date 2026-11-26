package com.example.programasuacactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProgramaAdaptador extends RecyclerView.Adapter<ProgramaAdaptador.ViewHolder> {

    private List<ProgramaUAC> listaProgramas;

    public ProgramaAdaptador(List<ProgramaUAC> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_programa_uac, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProgramaUAC item = listaProgramas.get(position);
        holder.txtPrograma.setText(item.getPrograma());
        holder.txtFacultad.setText(item.getFacultad());
        holder.txtDescripcion.setText(item.getDescripcion());

        Picasso.with(holder.itemView.getContext())
                .load(item.getImagenUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgPrograma);
    }

    @Override
    public int getItemCount() {
        return listaProgramas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPrograma;
        TextView txtPrograma, txtFacultad, txtDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPrograma = itemView.findViewById(R.id.img_programa);
            txtPrograma = itemView.findViewById(R.id.txt_programa);
            txtFacultad = itemView.findViewById(R.id.txt_facultad);
            txtDescripcion = itemView.findViewById(R.id.txt_descripcion);
        }
    }
}