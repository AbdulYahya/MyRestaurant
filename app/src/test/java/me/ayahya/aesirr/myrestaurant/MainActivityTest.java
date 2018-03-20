package me.ayahya.aesirr.myrestaurant;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import me.ayahya.aesirr.myrestaurant.ui.MainActivity;
import me.ayahya.aesirr.myrestaurant.ui.RestaurantsActivity;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)

public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() throws Exception {
        TextView appNameTextView = mainActivity.findViewById(R.id.mainHeaderTextView);
        assertThat(appNameTextView.getText().toString(), equalTo("Ready Player One?"));
    }

    @Test
    public void secondActivityStarted() {
        mainActivity.findViewById(R.id.findRestaurantsButton).performClick();

        Intent expectedIntent = new Intent(mainActivity, RestaurantsActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
