

package com.example.ecommerceappproject.data;


public interface PreferencesHelper {

    void save(String key, boolean value);

    void save(String key, String value);

    void save(String key, float value);

    void save(String key, int value);

    void save(String key, long value);

    boolean getBoolean(String key);

    String getString(String key);

    long getLong(String key);

    int getInt(String key);

    float getFloat(String key);

    void remove(String key);
}
