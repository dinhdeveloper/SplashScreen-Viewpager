package com.example.viewpagerscreenstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layout;
    private MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layoutLine);
        materialButton = findViewById(R.id.buttonOnboarding);

        setUpOnboardingItem();
        final ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(onboardingAdapter);

        setUpLayout();
        setCurrentLayout(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentLayout(position);
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });
    }

    private void setUpOnboardingItem() {
        List<OnboardingItem> onboardingItemList = new ArrayList<>();
        OnboardingItem itemPay = new OnboardingItem();
        itemPay.setTitle("Pay Your Bill Online");
        itemPay.setDescription("Electric bill payment is a feature of online,mobile and telephone banking");
        itemPay.setImage(R.drawable.smartphone);

        OnboardingItem itemOnTheWay = new OnboardingItem();
        itemOnTheWay.setTitle("Pay Your Bill Online");
        itemOnTheWay.setDescription("Electric bill payment is a feature of online,mobile and telephone banking");
        itemOnTheWay.setImage(R.drawable.business);

        OnboardingItem itemTogether = new OnboardingItem();
        itemTogether.setTitle("Pay Your Bill Online");
        itemTogether.setDescription("Electric bill payment is a feature of online,mobile and telephone banking");
        itemTogether.setImage(R.drawable.desk);

        onboardingItemList.add(itemPay);
        onboardingItemList.add(itemOnTheWay);
        onboardingItemList.add(itemTogether);

        onboardingAdapter = new OnboardingAdapter(onboardingItemList);
    }

    private void setUpLayout() {
        ImageView[] imageViews = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView(getApplicationContext());
            imageViews[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.onboarding_inactive));
            imageViews[i].setLayoutParams(layoutParams);
            layout.addView(imageViews[i]);
        }
    }

    private void setCurrentLayout(int index) {
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layout.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_inactive));
            }
        }
        if (index==onboardingAdapter.getItemCount()-1){
            materialButton.setText("Start");
            materialButton.setTransformationMethod(null);
        }else {
            materialButton.setText("Next");
            materialButton.setTransformationMethod(null);
        }
    }
}