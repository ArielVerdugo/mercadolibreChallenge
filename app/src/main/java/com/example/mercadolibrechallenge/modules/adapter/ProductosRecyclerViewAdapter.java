package com.example.mercadolibrechallenge.modules.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.s.Producto;
import com.example.mercadolibrechallenge.service.busqueda.OnItemClickListener;
import com.example.mercadolibrechallenge.utils.BaseFunctions;
import com.example.mercadolibrechallenge.utils.Format;

import java.util.List;

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.ProductoViewHolder>{


    private static Context context;
    private List<Producto>productos;
    private OnItemClickListener onItemClickListener;

    private String photo;


    public ProductosRecyclerViewAdapter(List<Producto>productos, OnItemClickListener onItemClickListener, Context context){
        this.productos = productos;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }



    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView price;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageProduct);
            title = itemView.findViewById(R.id.textViewTitle);
            price = itemView.findViewById(R.id.textViewPrice);
        }
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_card, parent, false);
        ProductoViewHolder vhProducto = new ProductoViewHolder(v);
        return vhProducto;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder,final int position) {

        photo = productos.get(position).getImagen();
        holder.price.setText(Format.formatDecimalSignal(productos.get(position).getPrecio()));
        holder.title.setText(productos.get(position).getTitulo());

        BaseFunctions.setPhotos(context,photo,holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });

        /*Glide.with((Activity) context)
                .load(productos.get(position).getImagen())
                .apply(new RequestOptions().override(100,100))
                .into(holder.imageView);
        */

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}
