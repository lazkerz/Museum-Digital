package com.example.museumdigital.admin.budaya.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mItems;

    public CustomSpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // Tambahkan atribut lain seperti font, dsb. sesuai kebutuhan
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // Tambahkan atribut lain seperti font, dsb. sesuai kebutuhan
        return view;
    }
}

