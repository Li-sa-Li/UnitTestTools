package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.common.base.Strings;

public class ImitateShowTextActivity extends Activity {
    public static final String KEY_EXTRA_MESSAGE = "com.example.android.testing.espresso.basicsample.MESSAGE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imitate_activity_show_text);
        TextView showText = (TextView) findViewById(R.id.show_text_tx);

        Intent intent = getIntent();
        String changeText = Strings.nullToEmpty(intent.getStringExtra(KEY_EXTRA_MESSAGE));
        showText.setText(changeText);
    }

    static protected Intent startNewIntent(Context context, String message) {
        Intent intent = new Intent(context, ImitateShowTextActivity.class);
        intent.putExtra(KEY_EXTRA_MESSAGE, message);
        return intent;
    }
}
