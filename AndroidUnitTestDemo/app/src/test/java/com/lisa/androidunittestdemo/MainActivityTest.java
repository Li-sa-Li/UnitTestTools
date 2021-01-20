package com.lisa.androidunittestdemo;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lisa.androidunittestdemo.ui.login.LoginActivity;

import junit.framework.TestCase;

import org.apache.tools.ant.Main;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowBroadcastReceiver;
import org.robolectric.shadows.ShadowLocationManager;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.FragmentTestUtil;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowLog.class}, sdk = 23)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "org.json.*", "sun.security.*", "javax.net.*"})
@PrepareForTest({MainActivity.class})
public class MainActivityTest extends TestCase {
    @Before
    public void setUp() {
        System.out.println("set up");
        //输出日志配置，用System.out代替Android的Log.x
        ShadowLog.stream = System.out;
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        System.out.println("after");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    @Test
    public void testOnClick() {
        final MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        Assert.assertNotNull(mainActivity);
        mainActivity.findViewById(R.id.login).performClick();

        final Intent expectedIntent = new Intent(mainActivity, LoginActivity.class);
        final Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        Assert.assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void testToast() {
        final MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.login).performClick();
        assertNotNull(ShadowToast.getLatestToast());

        Assert.assertEquals("测试", ShadowToast.getTextOfLatestToast());

        final List<Toast> shownToasts = shadowOf(RuntimeEnvironment.application).getShownToasts();
        Assert.assertThat(shownToasts.size(), is(1));
        Assert.assertEquals(Toast.LENGTH_SHORT, shownToasts.get(0).getDuration());
    }

    @Test
    public void showDialog() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        Assert.assertNull(dialog);

        mainActivity.findViewById(R.id.login).performClick();

        dialog = ShadowAlertDialog.getLatestAlertDialog();
        Assert.assertNotNull(dialog);

        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);
        Assert.assertEquals("测试showDialog", shadowAlertDialog.getMessage());
    }

    @Test
    public void testCheckBoxState() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.check_box);
        Assert.assertFalse(checkBox.isChecked());

        checkBox.performClick();

        Assert.assertTrue(checkBox.isChecked());

        checkBox.performClick();
        assertFalse(checkBox.isChecked());
    }

    @Test
    public void testFragment() {
        final TestFragment testFragment = TestFragment.newInstance("hello", "world");
//        SupportFragment
        FragmentTestUtil.startFragment(testFragment);
        assertNotNull(testFragment.getView());
        final TextView textView = testFragment.getView().findViewById(R.id.tv_name);
        assertNotNull(textView);

        assertEquals("hello", textView.getText());
    }

    @Test
    public void testResource() {
        Application application = RuntimeEnvironment.application;
        final String appName = application.getString(R.string.app_name);
        assertEquals("AndroidUnitTestDemo", appName);
    }

    @Test
    public void testLifecycle() {
        final ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        final MainActivity mainActivity = controller.get();
        assertNull(mainActivity.getLifecycleState());

        controller.create();
        assertEquals("onCreate", mainActivity.getLifecycleState());
    }

    @Test
    public void testBroadcastReceive() {
        final MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
//        LocalBroadcastManager broadcastManager =
    }

    @Test
    public void testBroadcastReceive3() {
        final Intent intent = new Intent("com.lisa.androidunittestdemo.MyReceiver");
        final PackageManager packageManager = RuntimeEnvironment.application.getPackageManager();
        final List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent, 0);
        assertNotNull(resolveInfos);
        assertThat(resolveInfos.size(), Matchers.greaterThan(0));
    }

    @Test
    public void testServiceLifeCycle() {
        final ServiceController<MyService> myServiceServiceController = Robolectric.buildService(MyService.class);
        myServiceServiceController.create();
        myServiceServiceController.startCommand(0, 0);
        myServiceServiceController.bind();
        myServiceServiceController.unbind();
        myServiceServiceController.destroy();
    }

    @Test
    public void testServiceCreate() {
        final MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.login).performClick();
        final Intent intent = new Intent(mainActivity, MyService.class);
        final Intent actual = ShadowApplication.getInstance().getNextStartedService();
        assertEquals(intent.getComponent(), actual.getComponent());
    }

    @Test
    public void addsDataToSharedPreference() {
        Application application = RuntimeEnvironment.application;
        SharedPreferences example = application.getSharedPreferences("example", Context.MODE_PRIVATE);
        Intent intent = new Intent(application, SampleIntentService.class);
        SampleIntentService sampleIntentService = new SampleIntentService();
        sampleIntentService.onHandleIntent(intent);
        assertNotSame("", example.getString("SAMPLE_DATA", ""), "");
    }
}