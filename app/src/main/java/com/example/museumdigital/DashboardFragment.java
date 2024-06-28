package com.example.museumdigital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.museumdigital.adapter.CarouselAdapter;
import com.example.museumdigital.adapter.Slider;
import com.example.museumdigital.admin.auth.view.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private ViewPager2 viewPager;
    private LinearLayout indicatorsContainer;
    private CarouselAdapter carouselAdapter;

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

        TextView textViewNavigateToSignIn = view.findViewById(R.id.Signin);
        textViewNavigateToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignIn();
            }
        });

        return view;
    }

    private void navigateToSignIn() {
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
    }

    // Method to set up indicators
    private void setupIndicators(LinearLayout indicatorsContainer) {
//        indicators = new ImageView[carouselAdapter.getItemCount()];
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(8, 0, 8, 0);
//        for (int i = 0; i < indicators.length; i++) {
//            indicators[i] = new ImageView(getContext());
//            indicators[i].setImageResource(R.drawable.indicator_inactive);
//            indicators[i].setLayoutParams(layoutParams);
//            indicatorsContainer.addView(indicators[i]);
//        }
    }

    // Method to set current indicator
    private void setCurrentIndicator(int position) {
        // Your implementation for setting current indicator
    }

}
