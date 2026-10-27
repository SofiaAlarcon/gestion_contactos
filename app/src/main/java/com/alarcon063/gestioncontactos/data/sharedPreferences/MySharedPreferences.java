package com.alarcon063.gestioncontactos.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
public class MySharedPreferences implements IMySharedPreferences {
    private static MySharedPreferences instance;
    private SharedPreferences sharedPreferences;

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("mi_agenda", Context.MODE_PRIVATE);
    }

    public static MySharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new MySharedPreferences(context);
        }
        return instance;
    }

    @Override
    public String getValue(String key, String value) {
        return sharedPreferences.getString(key, value);
    }

    @Override
    public void putValue(String key, String newValue) {
        sharedPreferences.edit().putString(key, newValue).apply();
    }
}
