package com.lisa.androidunittestdemo;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.manipulation.Ordering;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@Config(sdk = Build.VERSION_CODES.M)
//@Config(application = MyApplication.class)
//@Config(manifest = "",assetDir = "",resourceDir = "")
public class SandwichTest {
    @Config(sdk = Build.VERSION_CODES.KITKAT)
//    @Config(application = MyApplication.class)
    public void getSandwich_shouldReturnHamSandwich(){

    }

    @Config(qualifiers = "zh-rCN")
    @Test
    public void testString(){
//        final Context context = RuntimeEnvironment.application;
//        assertNotNull(context);
//        assertThat(context.getString(R.string.app_name),is("AndroidUnitTestDemo"));

    }
}
