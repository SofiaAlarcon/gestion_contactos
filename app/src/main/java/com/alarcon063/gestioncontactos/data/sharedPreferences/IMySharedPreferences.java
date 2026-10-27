package com.alarcon063.gestioncontactos.data.sharedPreferences;

public interface IMySharedPreferences {
    String getValue(String key, String value);
    void putValue(String key, String newValue);
}
