package com.example.androidproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerAdmin{
        private static final String SHARED_PREF_NAME = "Shared Pref for Admins";
        private static final int SHARED_PREF_PRIVATE = Context.MODE_PRIVATE;
        private static SharedPrefManagerAdmin ourInstance = null;
        private static SharedPreferences sharedPreferences = null;
        private SharedPreferences.Editor editor = null;
        static SharedPrefManagerAdmin getInstance(Context context) {
            if (ourInstance != null) {
                return ourInstance;
            }
            ourInstance=new SharedPrefManagerAdmin(context);
            return ourInstance;
        }
        private SharedPrefManagerAdmin(Context context) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                    SHARED_PREF_PRIVATE);
            editor = sharedPreferences.edit();
        }
        public boolean writeString(String key, String value) {
            editor.putString(key, value);
            return editor.commit();
        }
        public String readString(String key, String defaultValue) {
            return sharedPreferences.getString(key, defaultValue);
        }

}
