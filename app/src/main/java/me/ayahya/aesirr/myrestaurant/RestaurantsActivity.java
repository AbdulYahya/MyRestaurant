package me.ayahya.aesirr.myrestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantsActivity.class.getSimpleName();
    @BindView(R.id.locationTextView) TextView locationTextView;
    @BindView(R.id.listView) ListView listView;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
//                Log.wtf(TAG, "In the OnItemClick Listener");
//            }
//        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        String setLocationText = getString(R.string.here_be_restaurants, location);
        locationTextView.setText(setLocationText);
        getRestaurants(location);
    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        YelpService.findRestaurants(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                restaurants = yelpService.processResults(response);
                RestaurantsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] restaurantNames = new String[restaurants.size()];

                        // EXAMPLE OF HOW WE COULD MAKE USE OF A FOREACH LOOP
                        // API 15 DOES NOT SUPPORT THIS
//                        ArrayList<String> restaurantNames = new ArrayList<>();
//                        restaurants.forEach(restaurants -> restaurantNames.add(restaurants.getName());

                        for (final String restaurant: restaurantNames) {
                            restaurantNames[Arrays.asList(restaurantNames).indexOf(restaurant)] = restaurants.get(Arrays.asList(restaurantNames).indexOf(restaurant)).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(RestaurantsActivity.this,
                                android.R.layout.simple_list_item_1, restaurantNames);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
