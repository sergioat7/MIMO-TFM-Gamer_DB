/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 2/10/2020
 */

package es.upsa.mimo.gamerdb.activities;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    //MARK: - Constants

    private static final int TIME_TO_WAIT = 1500;
    private static final int PLATFORMS_COUNT = 51;
    private static final int GENRES_COUNT = 19;
    private static final int GAMES_COUNT = Constants.PAGE_SIZE;
    private static final String GAME_SEARCH = "witcher 3";

    //MARK: - Public properties

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //MARK: - Public methods

    @Test
    public void testInitialGames() {
        onView((withId(R.id.recycler_view_games))).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
    }

    @Test
    public void testPlatforms() {
        onView((withId(R.id.linear_layout_platforms))).check(matches(hasNumberOfChildren(PLATFORMS_COUNT)));
    }

    @Test
    public void testGenres() {
        onView((withId(R.id.linear_layout_genres))).check(matches(hasNumberOfChildren(GENRES_COUNT)));
    }

    @Test
    public void testShowFirstElement() {
        onView(withContentDescription("Desplazarse hacia arriba")).perform(click());
    }

    @Test
    public void testShowLastElement() {
        onView(withId(R.id.floating_action_button_end_list)).perform(click());
    }

    @Test
    public void testLoadMoreElements() {

        testInitialGames();
        testShowLastElement();
        onView(withId(R.id.button_load_more_items)).perform(click());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        onView((withId(R.id.recycler_view_games))).check(matches(hasNumberOfChildren(GAMES_COUNT + GAMES_COUNT + 1)));
    }

    @Test
    public void testSearchGames() {

        onView(withId(R.id.action_search)).perform(click());
        onView(withClassName(is("android.widget.SearchView$SearchAutoComplete"))).perform(replaceText(GAME_SEARCH));
        onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText(GAME_SEARCH))).perform(pressImeActionButton());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        testLoadMoreElements();
    }

    @Test
    public void testCancelSortPopup() {

        onView(withId(R.id.action_sort)).perform(click());
        onView(withId(android.R.id.content)).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button2), withText("Cancel"))).perform(click());
        onView(withId(android.R.id.content)).check(matches(anyOf(isDisplayed())));
    }

    @Test
    public void testSortGames() {

        onView(withId(R.id.action_sort)).perform(click());
        onView(withId(android.R.id.content)).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("Accept"))).perform(click());
        onView(withId(android.R.id.content)).check(matches(anyOf(isDisplayed())));
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        testLoadMoreElements();
    }

    @Test
    public void testGoToDetail() {
        onView(withId(R.id.recycler_view_games)).perform(RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    //MARK: - Private methods

    private static Matcher<View> hasNumberOfChildren(final int count) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {}

            @Override
            public boolean matchesSafely(View view) {

                if (view instanceof RecyclerView) {

                    RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
                    return adapter != null && adapter.getItemCount() == count;
                }
                return ((ViewGroup) view).getChildCount() == count;
            }
        };
    }

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
