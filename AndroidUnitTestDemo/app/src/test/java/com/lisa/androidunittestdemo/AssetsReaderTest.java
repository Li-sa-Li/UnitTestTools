package com.lisa.androidunittestdemo;

import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowLog.class},sdk = Build.VERSION_CODES.M)
public class AssetsReaderTest {
    private final static String TAG = "AssetsReaderTest";
    private AssetsReader assetsReader;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        assetsReader = new AssetsReader(RuntimeEnvironment.application);
    }

    @Test
    public void testAsset(){
        final String jsonData = assetsReader.read("test.json");
        Log.e(TAG, "testAsset: " +jsonData);
        try {
            final JSONObject jsonObject = new JSONObject(jsonData);
            assertThat(jsonObject.getString("name"),is("小明"));
            assertThat(jsonObject.getInt("id"),is(123));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
    }
}