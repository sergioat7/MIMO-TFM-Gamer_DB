/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 4/10/2020
 */

package es.upsa.mimo.gamerdb.activities;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import es.upsa.mimo.gamerdb.R;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GridImagesActivityTest {

    //MARK: - Public properties

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //MARK: - Public methods

    @Before
    public void setUp() {

        onView(withId(R.id.recycler_view_games)).perform(RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.button_view_images), isDisplayed())).perform(click());
    }

    @Test
    public void testGoToImage() {

        onData(anything()).inAdapterView(withId(R.id.grid_view_images)).atPosition(0).perform(click());
        onView(withId(R.id.image_view_game)).check(matches(isDisplayed()));
    }
}