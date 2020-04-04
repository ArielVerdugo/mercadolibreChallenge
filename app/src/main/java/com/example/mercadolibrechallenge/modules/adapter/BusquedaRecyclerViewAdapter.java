package com.example.mercadolibrechallenge.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;

import java.util.ArrayList;


public class BusquedaRecyclerViewAdapter  extends RecyclerView.Adapter<BusquedaRecyclerViewAdapter.BusquedaViewHolder> {


    private ArrayList<Integer> sampleImages;
    private static Context context;

    public BusquedaRecyclerViewAdapter(ArrayList<Integer> sampleImages, Context context){
        this.sampleImages = sampleImages;
        this.context = context;
    }


    public class BusquedaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public BusquedaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagenProducto);
        }
    }


    @NonNull
    @Override
    public BusquedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.busqueda_imagen_card, parent, false);
        BusquedaViewHolder vh = new BusquedaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BusquedaViewHolder holder, int position) {
        holder.imageView.setImageResource(sampleImages.get(position));

    }

    @Override
    public int getItemCount() {
        return sampleImages.size();
    }


}
