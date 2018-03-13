package me.ayahya.aesirr.myrestaurant;

import android.os.Build;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)

public class RestaurantsActivityTest {
//    private RestaurantsActivity restaurantsActivity;
    private ListView restaurantListView;

    @Before
    public void setup() {
        RestaurantsActivity restaurantsActivity;

        restaurantsActivity = Robolectric.setupActivity(RestaurantsActivity.class);
        restaurantListView = restaurantsActivity.findViewById(R.id.listView);
    }

    @Test
    public void checkRestaurantListViewPopulates() {
        assertNotNull(restaurantListView.getAdapter());
        assertEquals(restaurantListView.getAdapter().getCount(), 15);
    }
}
