package com.example.museumdigital.resep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museumdigital.core.model.Makanan.DataItem;
import com.example.museumdigital.R;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananViewHolder> {

    private Context context;
    private List<DataItem> makananList;
    private OnItemClickListener listener;

    public MakananAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<DataItem> makananList) {
        this.makananList = makananList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MakananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_budaya, parent, false);
        return new MakananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananViewHolder holder, int position) {
        DataItem makanan = makananList.get(position);
        holder.tvJudul.setText(makanan.getNamaMakanan());
        holder.tvDesc.setText(makanan.getDeskripsi());

        // Load image using Glide library
        if (makanan.getMedia() != null && makanan.getMedia().size() > 0) {
            Glide.with(context)
                    .load(makanan.getMedia().get(0)) // Assuming only one image URL is present
                    .placeholder(R.drawable.placeholder_image) // Placeholder image
                    .error(R.drawable.error_image) // Error image if loading fails
                    .centerCrop()
                    .into(holder.ivMakanan);
        } else {
            holder.ivMakanan.setImageResource(R.drawable.placeholder_image); // Default placeholder if no image URL
        }

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(makanan.getId()); // Pass ID to listener
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return makananList != null ? makananList.size() : 0;
    }

    public static class MakananViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMakanan;
        TextView tvJudul, tvDesc;

        public MakananViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMakanan = itemView.findViewById(R.id.ivBudaya);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int makananId);
    }
}
