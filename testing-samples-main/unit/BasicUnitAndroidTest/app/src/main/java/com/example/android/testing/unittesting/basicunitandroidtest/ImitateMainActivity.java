package com.example.android.testing.unittesting.basicunitandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImitateMainActivity extends Activity {
    public static final String KAY_DATA = "key_data";
    private EditText inputExt;
    private TextView historyLog;
    private ImitateLogHistory mImitateLogHistory;
    private SimpleDateFormat mSimpleDateFormat;
    private boolean mHistoryIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imitate_activity_main);
        inputExt = (EditText) findViewById(R.id.editText);
        historyLog = (TextView) findViewById(R.id.imitate_history);
        mImitateLogHistory = new ImitateLogHistory();
        List<Pair<String, Long>> data = mImitateLogHistory.getData();
        mSimpleDateFormat = new SimpleDateFormat("hh:mm:ss:SSS", Locale.getDefault());
        if (savedInstanceState != null) {
            mImitateLogHistory = savedInstanceState.getParcelable(KAY_DATA);
            final List<Pair<String, Long>> mImitateLogHistoryData = mImitateLogHistory.getData();
            for (Pair<String, Long> mImitateLogHistoryDatum : mImitateLogHistoryData) {
                appendEntryToView(mImitateLogHistoryDatum.first, mImitateLogHistoryDatum.second);
            }
        }
    }

    public void imitateUpdateHistory(View view) {
        String inputText = inputExt.getText().toString();
        long timeMillis = System.currentTimeMillis();
        appendEntryToView(inputText, timeMillis);
        mImitateLogHistory.addEntry(inputText, timeMillis);
    }

    private void appendEntryToView(String text, long timeStamp) {
        Date date = new Date(timeStamp);
        if (!mHistoryIsEmpty) {
            historyLog.append("\n");
        } else {
            historyLog.setText("");
        }
        historyLog.append(String.format("%s[%s]", text, mSimpleDateFormat.format(date)));
        mHistoryIsEmpty = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KAY_DATA, mImitateLogHistory);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
