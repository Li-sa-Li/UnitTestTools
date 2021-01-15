package com.example.android.testing.unittesting.BasicSample;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import static com.example.android.testing.unittesting.BasicSample.ImitateSharedPreferencesHelper.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImitateSharedPreferencesHelperTest {
    public static final String USER_NAME = "lisa";
    public static final String USER_EMAIL = "lisa@gmail.com";
    public static final Calendar DATE_OF_BIRTH = Calendar.getInstance();

    static {
        DATE_OF_BIRTH.set(1990, 2, 13);
    }

    private ImitateSharedPreferenceEntry sharedPreferenceEntry;
    private ImitateSharedPreferencesHelper mMockSharedPreferenceHelper;
    private ImitateSharedPreferencesHelper mBrokenMockSharedPreferenceHelper;

    @Mock
    SharedPreferences mMockSharedPreference;
    @Mock
    SharedPreferences mBrokenMockSharedPreference;
    @Mock
    SharedPreferences.Editor mEditor;
    @Mock
    SharedPreferences.Editor mMockEditor;

    @Before
    public void initMock() {
        sharedPreferenceEntry = new ImitateSharedPreferenceEntry(USER_NAME, DATE_OF_BIRTH, USER_EMAIL);
        mMockSharedPreferenceHelper = createMockSharedPreference();
        mBrokenMockSharedPreferenceHelper = createBrokenMockSharedPreference();

    }

    private ImitateSharedPreferencesHelper createMockSharedPreference() {

        when(mMockSharedPreference.getString(eq(KEY_USER_NAME), anyString()))
                .thenReturn(sharedPreferenceEntry.getUserName());
        when(mMockSharedPreference.getString(eq(KEY_EMAIL), anyString()))
                .thenReturn(sharedPreferenceEntry.getUserEmail());
        when(mMockSharedPreference.getLong(eq(KEY_AGE), anyLong()))
                .thenReturn(sharedPreferenceEntry.getDateOfBirth().getTimeInMillis());

        when(mEditor.commit()).thenReturn(true);

        when(mMockSharedPreference.edit()).thenReturn(mEditor);

        return new ImitateSharedPreferencesHelper(mMockSharedPreference);
    }

    private ImitateSharedPreferencesHelper createBrokenMockSharedPreference() {

        when(mMockEditor.commit()).thenReturn(false);
        when(mBrokenMockSharedPreference.edit()).thenReturn(mMockEditor);
        return new ImitateSharedPreferencesHelper(mBrokenMockSharedPreference);
    }

    @Test
    public void sharedPreference_saveAndReadPersonalInformation() {

        final boolean success = mMockSharedPreferenceHelper.imitateSavePersonalInfo(sharedPreferenceEntry);
        assertThat("checking that sharedPreference..save return true", success, is(true));

        final ImitateSharedPreferenceEntry personalInfo = mMockSharedPreferenceHelper.imitateGetPersonalInfo();
        assertThat("Checking that sharedPreferenceEntry.name has been persisted and read correctly",
                sharedPreferenceEntry.getUserName(), is(equalTo(personalInfo.getUserName())));
        assertThat("Checking that sharedPreferenceEntry.email has been persisted and read correctly",
                sharedPreferenceEntry.getUserEmail(), is(equalTo(personalInfo.getUserEmail())));

        assertThat("Checking that sharedPreferenceEnty.dateOfBirth has been persisted and read correctly",
                sharedPreferenceEntry.getDateOfBirth(), is(equalTo(personalInfo.getDateOfBirth())));
    }

    @Test
    public void sharedPreference_saveAndReadPersonalInformationFail_returnFalse() {
        final boolean success = mBrokenMockSharedPreferenceHelper.imitateSavePersonalInfo(sharedPreferenceEntry);
        assertThat("Make sure writing to a broken sharedPerferenceHelper return false "
                , success, is(false));
    }
}