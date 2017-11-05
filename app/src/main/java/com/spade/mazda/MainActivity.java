package com.spade.mazda;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.spade.mazda.cars.view.FragmentProducts;

public class MainActivity extends AppCompatActivity {

    private FrameLayout menuLayout;
    private ImageView closeImage;
    private float centerX, x2, y2, centerY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {

//        closeImage = findViewById(R.id.close_image_view);
//        final TextView homeText = findViewById(R.id.home_text_view);
//        final TextView productsText = findViewById(R.id.products_text_view);
//        TextView mazdaClubTextView = findViewById(R.id.mazda_club_text_view);
//        final TextView servicesTextView = findViewById(R.id.services_text_view);
//        TextView findUsTextView = findViewById(R.id.find_us_text_view);
//        menuLayout = findViewById(R.id.menu_view);
//        menuLayout.post(() -> {
//            centerX = closeImage.getX();
//            centerY = closeImage.getY();
//            Log.d("positions", homeText.getX() + " .. " + homeText.getY() + " .. " + closeImage.getX() + " .. " + closeImage.getY() + " .. ");
//            animate(productsText);
//
//        });


        FragmentProducts servicesFragment = new FragmentProducts();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, servicesFragment).commit();

//        menuLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            public void onGlobalLayout() {
//                int[] locations = new int[2];
//                int[] locations2 = new int[2];
//                closeImage.getLocationOnScreen(locations);
//                servicesTextView.getLocationOnScreen(locations2);
//                centerX = locations[0];
//                centerY = locations[1];
//                x2 = locations2[0];
//                y2 = locations2[1];
//                animate(servicesTextView);
////                Log.d("positions", x + " .. " + y);
//            }
//        });
//        int[] locations = new int[2];
//        closeImage.getLocationOnScreen(locations);
//        float centerX = locations[0];
//        float centerY = locations[1];


    }

    private void animate(View homeText) {
        ObjectAnimator translateXAnimation = ObjectAnimator.ofFloat(homeText, "translationX", centerX, 0);
        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(homeText, "translationY", centerY, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateXAnimation, translateYAnimation);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}
