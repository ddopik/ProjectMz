package com.spade.mazda.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.ui.cars.view.fragments.ProductsFragment;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.home.view.HomeFragment;
import com.spade.mazda.ui.services.view.fragments.ServicesFragment;

public class MainActivity extends AppCompatActivity {

    private int height;
    private FrameLayout menuLayout;
    private ImageView closeImage, logoImage;
    private LinearLayout openMenuLayout;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        logoImage = toolbar.findViewById(R.id.mazda_logo);

        setSupportActionBar(toolbar);

        init();
    }


    protected void init() {
        TextView homeText = findViewById(R.id.home_text_view);
        TextView productsText = findViewById(R.id.products_text_view);
        TextView mazdaClubTextView = findViewById(R.id.mazda_club_text_view);
        TextView servicesTextView = findViewById(R.id.services_text_view);
        TextView findUsTextView = findViewById(R.id.find_us_text_view);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        openMenuLayout = findViewById(R.id.open_menu_layout);
        closeImage = findViewById(R.id.close_image_view);
        menuLayout = findViewById(R.id.menu_view);

        homeText.setOnClickListener(view -> openHomeFragment());
        productsText.setOnClickListener(view -> openProductsFragment());
        findUsTextView.setOnClickListener(view -> openFindUsFragment());
        servicesTextView.setOnClickListener(view -> openServicesFragment());
        openMenuLayout.setOnClickListener(view -> showMenu());
        closeImage.setOnClickListener(view -> hideMenu());

        setScreenHeight();
        openHomeFragment();

    }


    protected void addFragment(BaseFragment baseFragment, String title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, baseFragment).commit();
        setTitle(title);
        hideMenu();
    }


    private void showMenu() {
        ObjectAnimator animTranslate = ObjectAnimator.ofFloat(menuLayout, "translationY", height, 0);
        animTranslate.setDuration(700);
        animTranslate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                menuLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                openMenuLayout.setClickable(false);
                closeImage.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animTranslate.start();
    }

    private void hideMenu() {
        ObjectAnimator animTranslate = ObjectAnimator.ofFloat(menuLayout, "translationY", 0, height);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animTranslate);
        animatorSet.setDuration(700);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                menuLayout.setVisibility(View.GONE);
                openMenuLayout.setClickable(true);
                closeImage.setClickable(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void openHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        addFragment(homeFragment, "");
        logoImage.setVisibility(View.VISIBLE);
    }

    private void openFindUsFragment() {
        FindUsFragment findUsFragment = new FindUsFragment();
        addFragment(findUsFragment, getString(R.string.find_us));
        logoImage.setVisibility(View.GONE);
    }

    private void openProductsFragment() {
        ProductsFragment productsFragment = new ProductsFragment();
        addFragment(productsFragment, getString(R.string.products));
        logoImage.setVisibility(View.GONE);
    }

    private void openServicesFragment() {
        ServicesFragment servicesFragment = new ServicesFragment();
        addFragment(servicesFragment, getString(R.string.services));
        logoImage.setVisibility(View.GONE);
    }

    private void setScreenHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

//    private void animate(View homeText) {
//        ObjectAnimator translateXAnimation = ObjectAnimator.ofFloat(homeText, "translationX", centerX, 0);
//        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(homeText, "translationY", centerY, 0);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(translateXAnimation, translateYAnimation);
//        animatorSet.setDuration(2000);
//        animatorSet.start();
//    }

//           menuLayout.post(() -> {
//            centerX = closeImage.getX();
//            centerY = closeImage.getY();
//            Log.d("positions", homeText.getX() + " .. " + homeText.getY() + " .. " + closeImage.getX() + " .. " + closeImage.getY() + " .. ");
//            animate(productsText);
//
//        });


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
