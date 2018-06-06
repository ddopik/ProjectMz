package com.spade.mazda.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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

import com.bumptech.glide.request.RequestOptions;
import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;
import com.spade.mazda.realm.RealmDbHelper;
import com.spade.mazda.realm.RealmDbImpl;
import com.spade.mazda.ui.about_us.view.AboutUsFragment;
import com.spade.mazda.ui.authentication.model.User;
import com.spade.mazda.ui.cars.view.fragments.ProductsFragment;
import com.spade.mazda.ui.find_us.view.fragments.FindUsFragment;
import com.spade.mazda.ui.home.view.ChangeLanguageDialogFragment;
import com.spade.mazda.ui.home.view.HomeFragment;
import com.spade.mazda.ui.mazda_club.view.MazdaClubFragment;
import com.spade.mazda.ui.profile.view.activity.ProfileActivity;
import com.spade.mazda.ui.services.view.fragments.ServicesFragment;
import com.spade.mazda.utils.GlideApp;
import com.spade.mazda.utils.PrefUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int height;
    private FrameLayout menuLayout;
    private ImageView closeImage, logoImage, userImage, tierImage;
    private LinearLayout aboutUs, appLang;
    private LinearLayout openMenuLayout;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private FrameLayout userImageLayout;


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
        userImageLayout = findViewById(R.id.profile_image_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        openMenuLayout = findViewById(R.id.open_menu_layout);
        closeImage = findViewById(R.id.close_image_view);
        menuLayout = findViewById(R.id.menu_view);
        userImage = findViewById(R.id.user_image);
        tierImage = findViewById(R.id.tier_image);
        appLang = findViewById(R.id.app_lang);
        aboutUs = findViewById(R.id.about_us);
        homeText.setOnClickListener(view -> openHomeFragment());
        productsText.setOnClickListener(view -> openProductsFragment());
        findUsTextView.setOnClickListener(view -> openFindUsFragment(FindUsFragment.SHOWROOMS_TYPE));
        servicesTextView.setOnClickListener(view -> openServicesFragment());
        mazdaClubTextView.setOnClickListener(view -> openMazdaClubFragment());
        openMenuLayout.setOnClickListener(view -> showMenu());
        closeImage.setOnClickListener(view -> hideMenu());
        userImageLayout.setOnClickListener(view -> startProfileActivity());
        appLang.setOnClickListener(view -> chooseLanguage());
        aboutUs.setOnClickListener(view -> {
            openAboutUsFragment();
        });
        setScreenHeight();
        openHomeFragment();
        setUserData();
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
        homeFragment.setOnNearestServiceClicked(() -> {
            openFindUsFragment(FindUsFragment.SERVICE_CENTER_TYPE);
        });
        addFragment(homeFragment, "");
        logoImage.setVisibility(View.VISIBLE);
    }

    private void openFindUsFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(FindUsFragment.EXTRA_POSITION, position);

        FindUsFragment findUsFragment = new FindUsFragment();
        findUsFragment.setArguments(bundle);
        addFragment(findUsFragment, getString(R.string.find_us));
        logoImage.setVisibility(View.GONE);
    }

    private void openAboutUsFragment() {
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        addFragment(aboutUsFragment, getString(R.string.about_us));
        logoImage.setVisibility(View.GONE);
        mDrawerLayout.closeDrawers();
    }

    private void openProductsFragment() {
        ProductsFragment productsFragment = new ProductsFragment();
        addFragment(productsFragment, getString(R.string.products));
        logoImage.setVisibility(View.GONE);
    }

    private void openMazdaClubFragment() {
        MazdaClubFragment mazdaClubFragment = new MazdaClubFragment();
        addFragment(mazdaClubFragment, getString(R.string.mazda_club));
        logoImage.setVisibility(View.GONE);
    }


    public void chooseLanguage() {
        new ChangeLanguageDialogFragment().show(getFragmentManager(), ChangeLanguageDialogFragment.class.getSimpleName());
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

    private void startProfileActivity() {
        if (PrefUtils.isLoggedIn(this)) {
            startActivity(ProfileActivity.getLaunchIntent(this));
        }
    }

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private void setUserData() {
        if (PrefUtils.isLoggedIn(this)) {
            RealmDbHelper realmDbHelper = new RealmDbImpl();
            User user = realmDbHelper.getUser(PrefUtils.getUserId(this));
            GlideApp.with(this).load(user.getImage()).
                    apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.ic_profile_default).into(userImage);
            tierImage.setImageResource(R.drawable.ic_tier_blue);
        } else {
            userImageLayout.setVisibility(View.GONE);
        }

    }

    public void changeLanguage(String lang) {
        Locale myLocale = new Locale(lang);
        Configuration conf = new Configuration();
        conf.locale = myLocale;

        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        if (lang.equals(PrefUtils.ARABIC_LANG))
            PrefUtils.setAppLang(this, PrefUtils.ARABIC_LANG);
        else
            PrefUtils.setAppLang(this, PrefUtils.ENGLISH_LANG);

        PrefUtils.setIsLanguageSelected(this, true);
        restartActivity();
    }

    public void restartActivity() {
        startActivity(getLaunchIntent(this));
        finish();
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
