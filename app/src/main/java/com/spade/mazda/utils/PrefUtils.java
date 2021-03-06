package com.spade.mazda.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ayman Abouzeid on 6/12/17.
 */

public class PrefUtils {
    private static final String PREF_FILE_NAME = "MAZDA_PREF_FILE";
    private static final String USER_ID = "USER_ID";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String NOTIFICATION_TOKEN = "NOTIFICATION_TOKEN";
    private static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";
    private static final String IS_LANGUAGE_SELECTED = "IS_LANGUAGE_SELECTED";
    private static final String IS_TOKEN_SAVED = "IS_TOKEN_SAVED";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String APP_LANG = "APP_LANG";
    public static final String ARABIC_LANG = "ar";
    public static final String ENGLISH_LANG = "en";
    public static final int GUEST_USER_ID = -1;
    public static final String IS_FINE_LOCATION = "ACCESS_FINE_LOCATION";

    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserID(Context context, int userID) {
        getSharedPref(context).edit().putInt(USER_ID, userID).apply();
    }

    public static void setUserToken(Context context, String userToken) {
        getSharedPref(context).edit().putString(USER_TOKEN, userToken).apply();
    }

    public static void setNotificationToken(Context context, String notificationToken) {
        getSharedPref(context).edit().putString(NOTIFICATION_TOKEN, notificationToken).apply();
    }

    public static void setIsFirstLaunch(Context context, boolean isFirstLaunch) {
        getSharedPref(context).edit().putBoolean(IS_FIRST_LAUNCH, isFirstLaunch).apply();
    }

    public static int getUserId(Context mContext) {
        return getSharedPref(mContext).getInt(USER_ID, GUEST_USER_ID);
    }

    public static String getUserToken(Context mContext) {
        return getSharedPref(mContext).getString(USER_TOKEN, "");
    }

    public static String getNotificationToken(Context mContext) {
        return getSharedPref(mContext).getString(NOTIFICATION_TOKEN, "");
    }

    public static boolean isFirstLaunch(Context context) {
        return getSharedPref(context).getBoolean(IS_FIRST_LAUNCH, true);
    }

    public static String getAppLang(Context context) {
        return getSharedPref(context).getString(APP_LANG, ENGLISH_LANG);
    }

    public static void setAppLang(Context context, String appLang) {
        getSharedPref(context).edit().putString(APP_LANG, appLang).apply();
    }

    public static boolean isLanguageSelected(Context context) {
        return getSharedPref(context).getBoolean(IS_LANGUAGE_SELECTED, false);
    }

    public static void setIsLanguageSelected(Context context, boolean isSelected) {
        getSharedPref(context).edit().putBoolean(IS_LANGUAGE_SELECTED, isSelected).apply();
    }

    public static boolean isLoggedIn(Context context) {
        return getSharedPref(context).getBoolean(IS_LOGGED_IN, false);
    }

    public static void setIsLoggedIn(Context context, boolean isLoggedIn) {
        getSharedPref(context).edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply();
    }

    public static boolean isTokenSaved(Context context) {
        return getSharedPref(context).getBoolean(IS_TOKEN_SAVED, false);
    }

    public static void setIsTokenSaved(Context context, boolean isSaved) {
        getSharedPref(context).edit().putBoolean(IS_TOKEN_SAVED, isSaved).apply();
    }

    public static boolean isFirstTimeAskingLocationPermission(Context context) {
        return getSharedPref(context).getBoolean(IS_FINE_LOCATION, true);
    }

    public static void firstTimeAskingLocationPermission(Context context, boolean permationState) {
        getSharedPref(context).edit().putBoolean(IS_FINE_LOCATION, permationState).apply();
    }

    public static void clearPrefUtils(Context context) {
        getSharedPref(context).edit().clear().apply();
    }

}
