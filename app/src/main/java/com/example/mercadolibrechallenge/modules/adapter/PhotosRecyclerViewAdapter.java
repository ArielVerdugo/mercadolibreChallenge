package com.example.mercadolibrechallenge.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadolibrechallenge.R;
import com.example.mercadolibrechallenge.model.s.PhotoResponse;
import com.example.mercadolibrechallenge.utils.BaseFunctions;

import java.io.IOException;
import java.util.List;

public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<PhotosRecyclerViewAdapter.PhotosViewHolder> {


    private List<PhotoResponse>photos;
    private Context context;

    public PhotosRecyclerViewAdapter(Context context,List<PhotoResponse>photos){
        this.photos = photos;
        this.context = context;
    }



    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        private ImageView photoView;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photoProduct);
        }
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_card, parent, false);

        PhotosViewHolder vhPhotos = new PhotosViewHolder(v);

        return vhPhotos;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        String secureUrl = photos.get(position).getSecure_url();

        BaseFunctions.setPhotos(context,secureUrl,holder.photoView);


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


}
