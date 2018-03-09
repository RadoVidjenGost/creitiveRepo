package com.vaivaidev.creitiveblog.creitiveblog.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Iva on 3/8/2018.
 */

public class SharedPreferencesManager {

    private static final String APP_PREFS = "AppPrefsFile";
    private static final String USER_TOKEN = "UserToken";

    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager instance;


    private SharedPreferencesManager(Context context) {
        sharedPrefs =
                context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void saveUserToken(String token) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String getUserToken() {
        String token = sharedPrefs.getString(USER_TOKEN, null);
        return token;
    }
}
