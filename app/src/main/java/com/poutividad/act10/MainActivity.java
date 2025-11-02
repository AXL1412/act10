package com.poutividad.act10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.poutividad.act10.adapters.PhotoPagerAdapter;
import com.poutividad.act10.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ViewPager2 viewPager;
    private LinearLayout indicatorLayout;
    private Button prevButton, nextButton;
    private List<Photo> photoList;
    private PhotoPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Loading final, correct, user-provided image URLs.");

        viewPager = findViewById(R.id.viewPager);
        indicatorLayout = findViewById(R.id.indicatorLayout);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        photoList = new ArrayList<>();
        // --- URLs FINALES Y CORRECTAS PROPORCIONADAS POR EL USUARIO ---
        photoList.add(new Photo("https://images.unsplash.com/photo-1619022497692-506a0c5c5b41", getString(R.string.photo_1_title), getString(R.string.photo_1_description)));
        photoList.add(new Photo("https://cdn.pixabay.com/photo/2024/05/26/10/21/image-9902070_1280.jpg", getString(R.string.photo_2_title), getString(R.string.photo_2_description)));
        photoList.add(new Photo("https://www.mundodeportivo.com/files/og_thumbnail/files/fp/uploads/2020/03/18/5e721557025a1.r_d.328-220.jpeg", getString(R.string.photo_3_title), getString(R.string.photo_3_description)));
        photoList.add(new Photo("https://invdes.com.mx/wp-content/uploads/2018/01/dunas-de-arena-invdes.jpg", getString(R.string.photo_4_title), getString(R.string.photo_4_description)));
        photoList.add(new Photo("https://pbs.twimg.com/media/Eq-wEEnXYAEe92t.jpg", getString(R.string.photo_5_title), getString(R.string.photo_5_description)));

        adapter = new PhotoPagerAdapter(this, photoList);
        viewPager.setAdapter(adapter);

        setupIndicators();
        updateIndicators(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(position);
            }
        });

        prevButton.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true));
        nextButton.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true));
    }

    private void setupIndicators() {
        ImageView[] indicators = new ImageView[photoList.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    this, R.drawable.indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            indicatorLayout.addView(indicators[i]);
        }
    }

    private void updateIndicators(int position) {
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) indicatorLayout.getChildAt(i);
            imageView.setImageDrawable(ContextCompat.getDrawable(
                    this,
                    i == position ? R.drawable.indicator_active : R.drawable.indicator_inactive
            ));
        }
    }
}
