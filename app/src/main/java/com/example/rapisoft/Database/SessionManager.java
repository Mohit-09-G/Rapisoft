package com.example.rapisoft.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void createLoginSession(String name, String email) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }


    public String getUserName() {
        return sharedPreferences.getString(KEY_NAME, null);
    }


    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
