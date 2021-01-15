package com.example.android.testing.unittesting.basicunitandroidtest;

import android.os.Parcel;
import android.util.Pair;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ImitateLogHistoryTest extends TestCase {
    private ImitateLogHistory mImitateLogHistory;
    public static final String TEST_STRING = "TEST_STRING";
    public static final long TEST_LONG = 12345678L;

    @Before
    public void createLogHistory() {
        mImitateLogHistory = new ImitateLogHistory();
    }

    @Test
    public void logHistory_ParcelableWriteRead() {
        mImitateLogHistory.addEntry(TEST_STRING, TEST_LONG);

        final Parcel obtain = Parcel.obtain();
        mImitateLogHistory.writeToParcel(obtain, mImitateLogHistory.describeContents());

        obtain.setDataPosition(0);

        final ImitateLogHistory fromParcel = ImitateLogHistory.CREATOR.createFromParcel(obtain);
        final List<Pair<String, Long>> data = fromParcel.getData();
        assertThat(data.size()).isEqualTo(1);
        assertThat(data.get(0).first).isEqualTo(TEST_STRING);
        assertThat(data.get(0).second).isEqualTo(TEST_LONG);
    }
}