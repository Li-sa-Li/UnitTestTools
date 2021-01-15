package com.example.android.testing.unittesting.BasicSample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ImitateMainActivity extends Activity {
    private static final String TAG = "ImitateMainActivity";
    private EditText mImitateUserNameInput;
    private DatePicker mImitateDobInputDatePicker;
    private EditText mImitateEmailInput;
    private ImitateEmailValidator mImitateEmailValidator;
    private ImitateSharedPreferencesHelper mImitateSharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imitate_activity_main);
        mImitateUserNameInput = (EditText) findViewById(R.id.imitate_user_name_input);
        mImitateDobInputDatePicker = (DatePicker) findViewById(R.id.imitate_date_of_birth_input);
        mImitateEmailInput = (EditText) findViewById(R.id.imitate_email_input);
        mImitateEmailValidator = new ImitateEmailValidator();
        mImitateEmailInput.addTextChangedListener(mImitateEmailValidator);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mImitateSharedPreferencesHelper = new ImitateSharedPreferencesHelper(defaultSharedPreferences);
        populateUi();
    }

    public void onImitateSaveClick(View view) {
        if (!mImitateEmailValidator.isValid()) {
            mImitateEmailInput.setError("Invalid email");
            Log.w(TAG, "Not save personal information:Invalid email ");
            return;
        }
        final String userName = mImitateUserNameInput.getText().toString();
        String email = mImitateEmailInput.getText().toString();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(mImitateDobInputDatePicker.getYear(), mImitateDobInputDatePicker.getMonth(), mImitateDobInputDatePicker.getDayOfMonth());
        final ImitateSharedPreferenceEntry imitateSharedPreferenceEntry = new ImitateSharedPreferenceEntry(userName, calendar, email);
        final boolean isSuccess = mImitateSharedPreferencesHelper.imitateSavePersonalInfo(imitateSharedPreferenceEntry);
        if (isSuccess) {
            Toast.makeText(this, "Save personal info success", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onImitateSaveClick: success to save personal info");
        } else {
            Log.e(TAG, "onImitateSaveClick: failed to save personal info to sharedPreferences");
        }
    }

    public void onImitateRevertClick(View view) {
        populateUi();
    }

    private void populateUi() {
        ImitateSharedPreferenceEntry personalInfo = mImitateSharedPreferencesHelper.imitateGetPersonalInfo();
        mImitateEmailInput.setText(personalInfo.getUserEmail());
        mImitateUserNameInput.setText(personalInfo.getUserName());
        final Calendar calendar = personalInfo.getDateOfBirth();
        mImitateDobInputDatePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MARCH), calendar.get(Calendar.DAY_OF_MONTH), null);
    }
}
