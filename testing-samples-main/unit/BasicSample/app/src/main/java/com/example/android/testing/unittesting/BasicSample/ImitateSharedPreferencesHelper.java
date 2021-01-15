package com.example.android.testing.unittesting.BasicSample;

import android.content.SharedPreferences;

import java.security.acl.LastOwnerException;
import java.util.Calendar;

public class ImitateSharedPreferencesHelper {
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_AGE = "age";
    public static final String KEY_EMAIL = "email";

    private final SharedPreferences mSharedPreferences;

    public ImitateSharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    /**
     * set personal info
     *
     * @param entry
     * @return
     */
    public boolean imitateSavePersonalInfo(ImitateSharedPreferenceEntry entry) {
        final SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(KEY_USER_NAME, entry.getUserName());
        edit.putString(KEY_EMAIL, entry.getUserEmail());
        edit.putLong(KEY_AGE, entry.getDateOfBirth().getTimeInMillis());
        return edit.commit();
    }

    /**
     * get personal info
     *
     * @return
     */
    public ImitateSharedPreferenceEntry imitateGetPersonalInfo() {
        String email = mSharedPreferences.getString(KEY_EMAIL, "");
        String userName = mSharedPreferences.getString(KEY_USER_NAME, "");
        Long dateOfBirth = mSharedPreferences.getLong(KEY_AGE, Calendar.getInstance().getTimeInMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateOfBirth);
        return new ImitateSharedPreferenceEntry(userName, calendar, email);
    }
}
