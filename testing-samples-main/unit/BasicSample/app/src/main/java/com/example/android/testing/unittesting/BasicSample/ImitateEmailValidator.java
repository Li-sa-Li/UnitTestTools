package com.example.android.testing.unittesting.BasicSample;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImitateEmailValidator implements TextWatcher {
    public static final String TAG = "ImitateEmailValidator";
    public static final Pattern IMITATE_EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+");
    private boolean mIsValid = false;

    public boolean isValid() {
        return mIsValid;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i(TAG, "beforeTextChanged: ");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i(TAG, "onTextChanged: ");
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i(TAG, "afterTextChanged: ");
        mIsValid = isValidEmail(s);
    }

    public static boolean isValidEmail(CharSequence email) {
        return email != null && IMITATE_EMAIL_PATTERN.matcher(email).matches();
    }
}
