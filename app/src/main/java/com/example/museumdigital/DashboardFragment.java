package com.example.museumdigital;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.museumdigital.adapter.CarouselAdapter;
import com.example.museumdigital.adapter.Slider;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;
    private CarouselAdapter carouselAdapter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        // You can add arguments if needed
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        viewPager = view.findViewById(R.id.vp_carousel);
        indicatorsContainer = view.findViewById(R.id.indicatorsContainer);

        // Initialize ViewPager adapter
        List<Slider> sliders = new ArrayList<>();
        sliders.add(new Slider(R.drawable.image_carousel));
        sliders.add(new Slider(R.drawable.image_carousel2));
        sliders.add(new Slider(R.drawable.image_carousel3));
        carouselAdapter = new CarouselAdapter(sliders);
        viewPager.setAdapter(carouselAdapter);

        // Set up indicators
        setupIndicators(indicatorsContainer);

        // Handle page change and auto-scroll
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
                if (position == carouselAdapter.getItemCount() - 1) {
                    // If last page, delay and return to the first page
                    new Handler().postDelayed(() -> viewPager.setCurrentItem(0, false), 2000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    // If user tries to scroll from the last page, immediately go to the first page
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem == carouselAdapter.getItemCount() - 1) {
                        viewPager.setCurrentItem(0, false);
                    }
                }
            }
        });

        // Auto-scroll runnable
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();
                int newPosition = (currentPosition == carouselAdapter.getItemCount() - 1) ? 0 : currentPosition + 1;
                viewPager.setCurrentItem(newPosition, true);
                handler.postDelayed(this, 5000); // 5000 milliseconds = 5 seconds
            }
        };
        handler.postDelayed(runnable, 5000);

        return view;
    }

    // Method to set up indicators
    private void setupIndicators(LinearLayout indicatorsContainer) {
        // Your implementation for setting up indicators
    }

    // Method to set current indicator
    private void setCurrentIndicator(int position) {
        // Your implementation for setting current indicator
    }

}
