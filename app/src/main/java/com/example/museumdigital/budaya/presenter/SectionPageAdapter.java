package com.example.museumdigital.budaya.presenter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.museumdigital.budaya.view.Alat_Musik_Fragment;
import com.example.museumdigital.budaya.view.Baju_Adat_Fragment;
import com.example.museumdigital.budaya.view.Tarian_Fragment;
import com.example.museumdigital.budaya.view.Tradisi_Fragment;

public class SectionPageAdapter extends FragmentStateAdapter {

    public SectionPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Baju_Adat_Fragment();
            case 1:
                return new Alat_Musik_Fragment();
            case 2:
                return new Tarian_Fragment();
            case 3:
                return new Tradisi_Fragment();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
}
