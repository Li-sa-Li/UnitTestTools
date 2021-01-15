package com.example.android.testing.unittesting.BasicSample;

import java.util.Calendar;

public class ImitateSharedPreferenceEntry {

    private final String mUserName;
    private final Calendar mDateOfBirth;
    private final String mUserEmail;

    public ImitateSharedPreferenceEntry(String userName, Calendar dateOfBirth, String userEmail) {
        mUserEmail = userEmail;
        mDateOfBirth = dateOfBirth;
        mUserName = userName;
    }

    public String getUserName() {
        return mUserName;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
    }

    public String getUserEmail() {
        return mUserEmail;
    }
}
