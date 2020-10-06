/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 6/10/2020
 */

package es.upsa.mimo.gamerdb.activities;

import android.content.pm.ActivityInfo;
import android.view.View;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.utils.Constants;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest {

    //MARK: - Constants

    private static final int TIME_TO_WAIT = 1500;
    private static final int GAMES_COUNT = Constants.PAGE_SIZE;
    private static final String GAME_SEARCH = "witcher 3";
    private static final String GAME_NAME = "THE WITCHER 3: WILD HUNT";

    //MARK: - Public properties

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //MARK: - Public methods

    @Test
    public void testRotationDevice() {

        onView(withId(R.id.action_search)).perform(click());
        onView(withClassName(is("android.widget.SearchView$SearchAutoComplete"))).perform(replaceText(GAME_SEARCH));
        onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText(GAME_SEARCH))).perform(pressImeActionButton());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));

        onView(allOf(withId(R.id.text_view_name), withText(GAME_NAME))).check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view_games)).perform(RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        onView(withContentDescription("Desplazarse hacia arriba")).perform(click());

        onView(allOf(withId(R.id.text_view_name), withText(GAME_NAME))).check(matches(isDisplayed()));
    }

    //MARK: - Private methods

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}
