package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ImitateMainActivity extends Activity implements View.OnClickListener {

    private EditText mUserInput;
    private TextView mImitateTextToChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imitate_activity_main);

        findViewById(R.id.imitateActivityTextChBt).setOnClickListener(this);
        findViewById(R.id.imitateChangeTextBt).setOnClickListener(this);
        mUserInput = (EditText) findViewById(R.id.imitateEditTextUserInput);
        mImitateTextToChange = (TextView) findViewById(R.id.imitateTextToBeChange);
    }

    @Override
    public void onClick(View v) {
        final String message = mUserInput.getText().toString();
        if (v.getId() == R.id.imitateChangeTextBt) {
            mImitateTextToChange.setText(message);
        } else if (v.getId() == R.id.imitateActivityTextChBt) {
            Intent intent = ImitateShowTextActivity.startNewIntent(this, message);
            startActivity(intent);
        }
    }
}
