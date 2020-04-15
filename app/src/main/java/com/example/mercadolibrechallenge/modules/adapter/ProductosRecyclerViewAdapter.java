package com.example.mercadolibrechallenge.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.Producto;
import com.example.mercadolibrechallenge.service.busqueda.OnItemClickListener;
import com.example.mercadolibrechallenge.utils.BaseFunctions;
import com.example.mercadolibrechallenge.utils.Format;

import java.util.List;

public class ProductosRecyclerViewAdapter extends RecyclerView.Adapter<ProductosRecyclerViewAdapter.ProductoViewHolder>{


    private Context context;
    private List<Producto>productos;
    private OnItemClickListener onItemClickListener;

    private String photo;
    private String delivery;


    public ProductosRecyclerViewAdapter(List<Producto>productos, OnItemClickListener onItemClickListener, Context context){
        this.productos = productos;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }



    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView price;
        private TextView delivery;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageProduct);
            title = itemView.findViewById(R.id.textViewTitle);
            price = itemView.findViewById(R.id.textViewPrice);
            delivery = itemView.findViewById(R.id.textViewDelivery);
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
        delivery = BaseFunctions.getShipping(productos.get(position).getShiping().getFree());
        holder.price.setText(Format.formatDecimalSignal(productos.get(position).getPrecio()));
        holder.title.setText(productos.get(position).getTitulo());
        holder.delivery.setText(delivery);

        BaseFunctions.setPhotos(context,photo,holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}
