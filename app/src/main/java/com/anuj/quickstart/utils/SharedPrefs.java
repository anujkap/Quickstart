package com.anuj.quickstart.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SharedPrefs {

    private static SharedPrefs instance = null;
    private SharedPreferences prefs;

    private static final String APP_PREFS = "app_prefs";

    private static final String AUTH_TOKEN = "auth_token";


    public static SharedPrefs getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefs(context);
        }
        return instance;
    }

    private SharedPrefs(Context context) {
        prefs = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public String getAuthToken() {
        return prefs.getString(AUTH_TOKEN, "");
    }

}
