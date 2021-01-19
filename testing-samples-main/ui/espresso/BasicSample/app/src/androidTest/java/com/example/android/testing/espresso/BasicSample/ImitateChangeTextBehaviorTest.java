package com.example.android.testing.espresso.BasicSample;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ImitateChangeTextBehaviorTest {
    public static final String SHOW_TEXT = "Espresso";

    @Before
    public void before() {
        System.out.println(" before ");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @Rule
    public ActivityScenarioRule<ImitateMainActivity> imitateMainActivityActivityScenario = new ActivityScenarioRule<>(ImitateMainActivity.class);

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.imitateEditTextUserInput)).perform(typeText(SHOW_TEXT), closeSoftKeyboard());
        onView(withId(R.id.imitateChangeTextBt)).perform(click());

        onView(withId(R.id.imitateTextToBeChange)).check(matches(withText(SHOW_TEXT)));
    }

    @Test
    public void changeText_newActivity() {
        onView(withId(R.id.imitateEditTextUserInput)).perform(typeText(SHOW_TEXT), closeSoftKeyboard());
        onView(withId(R.id.imitateActivityTextChBt)).perform(click());

        onView(withId(R.id.show_text_tx)).check(matches(withText(SHOW_TEXT)));
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }
}
