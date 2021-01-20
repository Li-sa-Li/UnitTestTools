package com.lisa.androidunittestdemo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class SampleIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SampleIntentService() {
        super("SampleIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("example", Context.MODE_PRIVATE).edit();
        editor.putString("SAMPLE_DATA", "sample data");
        editor.apply();
    }
}
