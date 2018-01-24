package com.rspitaliere.starwarscharacters.dao;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by rspitaliere on 24/07/17.
 */

public class SharedLocalDAO {

    public final String PREF_FILE_NAME = "APP_CONFIG";
    public Context context;

    public SharedLocalDAO(Context context) {
        this.context = context;
    }

    public void save(String key, String value) {
        SharedPreferences preferences = this.context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        SharedPreferences preferences = this.context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        return preferences.getString(key, null);
    }

}
