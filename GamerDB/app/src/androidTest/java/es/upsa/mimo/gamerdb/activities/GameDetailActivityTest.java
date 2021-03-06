/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 5/10/2020
 */

package es.upsa.mimo.gamerdb.activities;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import es.upsa.mimo.gamerdb.R;
import es.upsa.mimo.gamerdb.utils.Constants;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GameDetailActivityTest {

    //MARK: - Constants

    private static final int TIME_TO_WAIT = 1500;
    private static final int GAMES_COUNT = Constants.PAGE_SIZE;

    //MARK: - Public properties

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //MARK: - Public methods

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
    }

    @Test
        public void testCorrectMainGameData() {

        goToDetail();

        onView(withId(R.id.button_watch_video)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.button_view_images)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.text_view_game_description)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.button_show_more_text)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.linear_layout_stores))).check(matches(hasNumberOfChildren(-1)));

        onView(withId(R.id.text_view_game_series_title)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_game_series)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.recycler_view_game_series))).check(matches(hasNumberOfChildren(-1)));
        onView(childAtPosition(childAtPosition(withId(R.id.recycler_view_game_series), 0), 2)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_games_suggested_title)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_games_suggested)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.recycler_view_games_suggested))).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
        onView(childAtPosition(childAtPosition(withId(R.id.recycler_view_games_suggested), 0), 2)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_game_additions_title)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_game_additions)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.recycler_view_game_additions))).check(matches(hasNumberOfChildren(-1)));
        onView(childAtPosition(childAtPosition(withId(R.id.recycler_view_game_additions), 0), 2)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_developers_title)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_developers)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.recycler_view_developers))).check(matches(hasNumberOfChildren(-1)));

        onView(withId(R.id.text_view_achievements_title)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_achievements)).perform(scrollTo()).check(matches(isDisplayed()));
        onView((withId(R.id.recycler_view_achievements))).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
    }

    @Test
    public void testShowCompleteDescription() {

        goToDetail();

        onView(allOf(withId(R.id.button_show_more_text), isDisplayed())).perform(scrollTo(), click());
        onView(withId(R.id.text_view_platforms_title)).perform(scrollTo());
        onView(withId(R.id.button_show_more_text)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testAccessToWebsite() {

        goToDetail();

        onView(allOf(withId(R.id.text_view_website))).perform(scrollTo(), click());
    }

    @Test
    public void testAccessToGameSeries() {

        goToDetail();

        onView(withId(R.id.recycler_view_game_series)).perform(scrollTo(), RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testAccessToGameSuggested() {

        goToDetail();

        onView(withId(R.id.recycler_view_games_suggested)).perform(scrollTo(), RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testAccessToGameEdition() {

        goToDetail();

        onView(withId(R.id.recycler_view_game_additions)).perform(scrollTo(), RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testGameWithoutVideoAndImages() {

        searchGame("untitled carnivores game");

        onView(withId(R.id.button_watch_video)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_view_images)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testGameWithoutDescription() {

        searchGame("astro's playroom");

        onView(withId(R.id.text_view_game_description)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_show_more_text)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testGameWithoutExtraData() {

        searchGame("poker club");

        onView(withId(R.id.text_view_tags)).perform(scrollTo());

        onView(withId(R.id.text_view_game_series_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recycler_view_game_series)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_games_suggested_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recycler_view_games_suggested)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_game_additions_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recycler_view_game_additions)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_developers_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recycler_view_developers)).check(matches(not(isDisplayed())));

        onView(withId(R.id.text_view_achievements_title)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recycler_view_achievements)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testLoadMoreGamesSuggested() {

        goToDetail();

        onView((withId(R.id.recycler_view_games_suggested))).perform(scrollTo()).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
        onView((withId(R.id.recycler_view_games_suggested))).perform(RecyclerViewActions.scrollToPosition(GAMES_COUNT));
        onView(allOf(childAtPosition(withId(R.id.recycler_view_games_suggested), 1), withId(R.id.constraint_layout_load_more_items))).perform(click());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        onView((withId(R.id.recycler_view_games_suggested))).check(matches(hasNumberOfChildren(GAMES_COUNT + GAMES_COUNT + 1)));
        onView((withId(R.id.recycler_view_achievements))).perform(scrollTo()).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
    }

    @Test
    public void testLoadMoreAchievements() {

        goToDetail();

        onView((withId(R.id.recycler_view_achievements))).perform(scrollTo()).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
        onView((withId(R.id.recycler_view_achievements))).perform(RecyclerViewActions.scrollToPosition(GAMES_COUNT));
        onView(allOf(childAtPosition(withId(R.id.recycler_view_achievements), 1), withId(R.id.constraint_layout_load_more_items))).perform(click());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        onView((withId(R.id.recycler_view_achievements))).check(matches(hasNumberOfChildren(GAMES_COUNT + GAMES_COUNT + 1)));
        onView((withId(R.id.recycler_view_games_suggested))).perform(scrollTo()).check(matches(hasNumberOfChildren(GAMES_COUNT + 1)));
    }

    @Test
    public void testGoToVideo() {

        goToDetail();

        onView(allOf(withId(R.id.button_watch_video), isDisplayed())).perform(click());
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
        onView(withId(R.id.web_view_video)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoToImages() {

        goToDetail();

        onView(allOf(withId(R.id.button_view_images), isDisplayed())).perform(click());
        onView(withId(R.id.grid_view_images)).check(matches(isDisplayed()));
    }

    @Test
    public void testGoBack() {

        goToDetail();

        onView(withContentDescription("Desplazarse hacia arriba")).perform(click());
    }

    //MARK: - Private methods

    private void searchGame(String name) {

        onView(withId(R.id.action_search)).perform(click());
        onView(withClassName(is("android.widget.SearchView$SearchAutoComplete"))).perform(replaceText(name));
        onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText(name))).perform(pressImeActionButton());
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));

        goToDetail();
    }

    private void goToDetail() {

        onView(withId(R.id.recycler_view_games)).perform(RecyclerViewActions.scrollToPosition(0), RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(waitFor(TIME_TO_WAIT));
    }

    private static Matcher<View> hasNumberOfChildren(final int count) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {}

            @Override
            public boolean matchesSafely(View view) {

                if (view instanceof RecyclerView) {

                    RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
                    if (count < 0) {
                        return adapter != null && adapter.getItemCount() > 0;
                    } else {
                        return adapter != null && adapter.getItemCount() == count;
                    }
                }

                if (count < 0) {
                    return ((ViewGroup) view).getChildCount() > 0;
                } else {
                    return ((ViewGroup) view).getChildCount() == count;
                }
            }
        };
    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {}

            @Override
            public boolean matchesSafely(View view) {

                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
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
