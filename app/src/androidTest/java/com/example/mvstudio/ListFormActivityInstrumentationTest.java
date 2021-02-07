package com.example.mvstudio;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

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
@LargeTest

public class ListFormActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<ListFormActivity> activityTestRule =
            new ActivityTestRule<>(ListFormActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.editTextMovieName)).perform(typeText("Enola Holmes"))
                .check(matches(withText("Enola Holmes")));
        onView(withId(R.id.editTextMovieCategory)).perform(typeText("Adventure"))
                .check(matches(withText("Adventure")));
        onView(withId(R.id.editTextMovieDetail)).perform(typeText("Great story"))
                .check(matches(withText("Great Story")));
    }

    public void movieIsSentToWatchListActivity(){
        String movieName = "Enola";
        onView(withId(R.id.editTextMovieName)).perform(typeText(movieName)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.ListButton)).perform(click());
        onView(withId(R.id.editTextMovieName)).check(matches
                (withText("Here are all the movie: " + movieName)));
    }

    @Test
    public void categoryIsSentToWatchListActivity(){
        String movieCategory = "Adventure";
        onView(withId(R.id.editTextMovieCategory)).perform(typeText(movieCategory)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.ListButton)).perform(click());
        onView(withId(R.id.editTextMovieCategory)).check(matches
                (withText("Here are all the Category: " + movieCategory)));
    }

    @Test
    public void detailIsSentToWatchListActivity(){
        String movieDetail = "Great Story";
        onView(withId(R.id.editTextMovieDetail)).perform(typeText(movieDetail)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e){
            System.out.println("got interrupted!");
        }
        onView(withId(R.id.ListButton)).perform(click());
        onView(withId(R.id.editTextMovieDetail)).check(matches
                (withText("Here are all the Detail: " + movieDetail)));
    }




}

