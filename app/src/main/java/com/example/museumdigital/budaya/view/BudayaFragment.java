package com.example.museumdigital.budaya.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.museumdigital.R;
import com.example.museumdigital.budaya.presenter.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BudayaFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    public BudayaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_budaya, container, false);

        viewPager = rootView.findViewById(R.id.vp_budaya);
        tabLayout = rootView.findViewById(R.id.tl_budaya);

        // Create instance of SectionPagerAdapter
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getChildFragmentManager(), getLifecycle());

        // Set adapter to ViewPager
        viewPager.setAdapter(sectionPageAdapter);

        // Attach TabLayout to ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Baju Adat");
                    break;
                case 1:
                    tab.setText("Alat Musik");
                    break;
                case 2:
                    tab.setText("Tarian");
                    break;
                case 3:
                    tab.setText("Tradisi");
                    break;
            }
        }).attach();

        return rootView;
    }
}
