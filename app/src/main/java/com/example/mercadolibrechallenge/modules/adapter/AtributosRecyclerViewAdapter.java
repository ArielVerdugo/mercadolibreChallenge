package com.example.mercadolibrechallenge.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.s.Atributo;

import java.util.List;


public class AtributosRecyclerViewAdapter extends RecyclerView.Adapter<AtributosRecyclerViewAdapter.AtributosViewHolder>{

    private Context contexto;
    private List<Atributo> atributos;


    public AtributosRecyclerViewAdapter(Context contexto,List<Atributo> atributos){
        this.contexto = contexto;
        this.atributos = atributos;
    }

    public class AtributosViewHolder extends RecyclerView.ViewHolder {
        private TextView titleAtributo;
        private TextView valueAtributo;

        public AtributosViewHolder(@NonNull View itemView) {
            super(itemView);
            titleAtributo  = itemView.findViewById(R.id.titleAtributo);
            valueAtributo = itemView.findViewById(R.id.valueAtributo);
        }
    }

    @NonNull
    @Override
    public AtributosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_atributos_detalle, parent, false);

        AtributosViewHolder vhAtributo = new AtributosViewHolder(v);
        return vhAtributo;

    }

    @Override
    public void onBindViewHolder(@NonNull AtributosViewHolder holder, int position) {
        Atributo atributo = this.atributos.get(position);
        holder.titleAtributo.setText(atributo.getName());

        if ( atributo.getDescripcionName() != null && !atributo.getDescripcionName().isEmpty()) {
            holder.valueAtributo.setText(atributo.getDescripcionName());
        } else {
            if (atributo.getAtributoConfiguracion() != null
                    && atributo.getAtributoConfiguracion().getNumber() != null
                    && atributo.getAtributoConfiguracion().getUnit() != null) {
                holder.valueAtributo.setText(atributo.getAtributoConfiguracion().getNumber() + " " + atributo.getAtributoConfiguracion().getUnit());
            }
        }

    }

    @Override
    public int getItemCount() {
        return atributos.size();
    }


}
