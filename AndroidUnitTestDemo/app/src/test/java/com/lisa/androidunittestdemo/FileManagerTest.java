package com.lisa.androidunittestdemo;

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowLog.class}, sdk = Build.VERSION_CODES.M)
public class FileManagerTest {
    private final static String TAG = "FileManagerTest";
    private FileManager fileManager;
    private String SDCARD_ROOT;

    @Before
    public void setUp() {
        Log.i(TAG, "setUp: ");
        ShadowLog.stream = System.out;
        fileManager = new FileManager();
        SDCARD_ROOT = Environment.getExternalStorageDirectory().getPath();
    }

    @Test
    public void testFile() {
        Log.e(TAG, "testFile: " + SDCARD_ROOT);
        String dir = SDCARD_ROOT + "/test/aaa";
        File file = new File(dir);
        file.mkdirs();
        assertTrue(file.exists());

        String fullName = dir + "/bbb.txt";
        fileManager.write(fullName, "测试testFile");

        File file1 = new File(fullName);
        assertTrue(file1.exists());
        Log.e(TAG, "testFile file=: " + file1.getPath());
        assertEquals("测试testFile", fileManager.read(fullName));

        file1.delete();
        assertFalse(file1.exists());
    }

    @After
    public void tearDown() {
        System.out.println("tear down");
    }
}