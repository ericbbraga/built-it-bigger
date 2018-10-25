package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
        new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void checkIfAsyncTaskGetNonEmptyMessage() {
        Espresso.onView(
            ViewMatchers.withId(R.id.tell_joke_widget)
        ).perform(
            ViewActions.click()
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.joke_message)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    not("")
                )
            )
        );
    }

    @After
    public void tearDown() {
        mActivityTestRule.finishActivity();
    }

}