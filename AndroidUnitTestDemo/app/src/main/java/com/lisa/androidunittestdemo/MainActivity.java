package com.lisa.androidunittestdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lisa.androidunittestdemo.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin;
    private String lifecycle;
    public static final String TAG = "MainActivity";
    public static final String ACTION_TEST = "com.fly.unit.test";
    public static final String ACTION_TEST2 = "com.fly.unit.test2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifecycle = "onCreate";
        initView();
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.login);
        mBtnLogin.setOnClickListener(this);
    }

    private BroadcastReceiver mTestBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "mTestBroadcastReceiver onReceive: " + intent.getAction());
            if (ACTION_TEST.equals(intent.getAction())) {
                final String name = intent.getStringExtra("name");
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString("name", name).apply();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
                showDialog();
                break;
            default:
                break;
        }
    }

    public String getLifecycleState() {
        return lifecycle;
    }

    public void showDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("测试showDialog")
                .setTitle("提示")
                .create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        lifecycle = "onResume";
        IntentFilter intentFilter = new IntentFilter(ACTION_TEST);
        intentFilter.addAction(ACTION_TEST2);
        LocalBroadcastManager.getInstance(this).registerReceiver(mTestBroadCastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
        lifecycle = "onPause";
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mTestBroadCastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        lifecycle = "onDestroy";
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        lifecycle = "onStop";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
        lifecycle = "onRestart";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        lifecycle = "onStart";
    }
}