package com.lisa.androidunittestdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferManager {
    private Context mCxt;

    public SharePreferManager(Context context) {
        mCxt = context;
    }

    public void saveName(String key, String name) {
        SharedPreferences sp = mCxt.getSharedPreferences("test", Context.MODE_PRIVATE);
        sp.edit().putString(key, name).apply();
    }

    public String getName(String key) {
        final SharedPreferences sp = mCxt.getSharedPreferences("test", Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
