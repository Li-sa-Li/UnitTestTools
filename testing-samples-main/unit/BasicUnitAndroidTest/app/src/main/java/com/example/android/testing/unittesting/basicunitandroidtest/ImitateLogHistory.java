package com.example.android.testing.unittesting.basicunitandroidtest;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ImitateLogHistory implements Parcelable {
    private List<Pair<String, Long>> mData = new ArrayList<>();

    public ImitateLogHistory() {
    }

    protected ImitateLogHistory(Parcel in) {
        int length = in.readInt();
        String[] texts = new String[length];
        long[] timeStamp = new long[length];
        in.readStringArray(texts);
        in.readLongArray(timeStamp);
        if (texts.length != timeStamp.length) {
            throw new IllegalStateException("Error reading from saved state.");
        }
        mData.clear();
        for (int i = 0; i < length; i++) {
            Pair<String, Long> pair = new Pair<>(texts[i], timeStamp[i]);
            mData.add(pair);
        }
    }

    public static final Creator<ImitateLogHistory> CREATOR = new Creator<ImitateLogHistory>() {
        @Override
        public ImitateLogHistory createFromParcel(Parcel in) {
            return new ImitateLogHistory(in);
        }

        @Override
        public ImitateLogHistory[] newArray(int size) {
            return new ImitateLogHistory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void addEntry(String text, long timeStamp) {
        mData.add(new Pair<String, Long>(text, timeStamp));
    }

    public List<Pair<String, Long>> getData() {
        return new ArrayList<>(mData);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] texts = new String[mData.size()];
        long[] timeTemps = new long[mData.size()];

        for (int i = 0; i < mData.size(); i++) {
            texts[i] = mData.get(i).first;
            timeTemps[i] = mData.get(i).second;
        }
        dest.writeInt(texts.length);
        dest.writeStringArray(texts);
        dest.writeLongArray(timeTemps);
    }
}
