package com.example.museumdigital.budaya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museumdigital.R;
import com.example.museumdigital.core.model.Budaya.DataBudaya;

import java.util.List;

public class BudayaAdapter extends RecyclerView.Adapter<BudayaAdapter.BudayaViewHolder> {

    private Context context;
    private List<DataBudaya> budayaList;
    private OnItemClickListener listener;

    public BudayaAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<DataBudaya> budayaList) {
        this.budayaList = budayaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BudayaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_budaya, parent, false);
        return new BudayaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudayaViewHolder holder, int position) {
        DataBudaya budaya = budayaList.get(position);
        holder.tvJudul.setText(budaya.getNamaBudaya());
        holder.tvDesc.setText(budaya.getDeskripsi());

        // Load image using Glide library
        if (budaya.getMedia() != null && budaya.getMedia().size() > 0) {
            Glide.with(context)
                    .load(budaya.getMedia().get(0)) // Assuming only one image URL is present
                    .placeholder(R.drawable.placeholder_image) // Placeholder image
                    .error(R.drawable.error_image) // Error image if loading fails
                    .centerCrop()
                    .into(holder.ivBudaya);
        } else {
            holder.ivBudaya.setImageResource(R.drawable.placeholder_image); // Default placeholder if no image URL
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(budaya.getId()); // Pass ID to listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return budayaList != null ? budayaList.size() : 0;
    }

    public static class BudayaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBudaya;
        TextView tvJudul, tvDesc;

        public BudayaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBudaya = itemView.findViewById(R.id.ivBudaya);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int budayaId);
    }
}
