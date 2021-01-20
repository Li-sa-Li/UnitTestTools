package com.lisa.androidunittestdemo;

import android.os.Build;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowLog.class},sdk = Build.VERSION_CODES.M)
public class SharePreferManagerTest {

    private SharePreferManager sharePreferManager;

    @Before
    public void setUp()  {
        sharePreferManager = new SharePreferManager(RuntimeEnvironment.application);

    }

    @Test
    public void testSp(){
        sharePreferManager.saveName("name","lisa");
        assertEquals("lisa",sharePreferManager.getName("name"));
    }

    @After
    public void tearDown() {
    }
}