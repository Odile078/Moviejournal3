package com.example.mvstudio;

import android.view.View;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;
@RunWith(AndroidJUnit4.class)
@LargeTest

public class MovieActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MovieActivity> activityTestRule =
            new ActivityTestRule<>(MovieActivity.class);
    @Test
    public void listItemClickDisplaysToastWithCorrectMovie() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String movieName = "Enola Holmes";
        onData(anything())
                .inAdapterView(withId(R.id.androidList))
                .atPosition(0)
                .perform(click());
        onView(withText(movieName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(movieName)));
    }
}
