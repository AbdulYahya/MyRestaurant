package me.ayahya.aesirr.myrestaurant;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;

import me.ayahya.aesirr.myrestaurant.ui.RestaurantsActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

public class RestaurantsActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<RestaurantsActivity> restaurantsActivityActivityTestRule =
            new ActivityTestRule<>(RestaurantsActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectRestaurant() {
        View activityDecorView = restaurantsActivityActivityTestRule.getActivity().getWindow().getDecorView();
        String restaurantName = "Kentucky Fried Chicken";

        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(14)
                .perform(click());
        onView(withText(restaurantName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(restaurantName)));
    }
}
