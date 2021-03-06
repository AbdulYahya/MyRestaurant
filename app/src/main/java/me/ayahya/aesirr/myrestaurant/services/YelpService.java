package me.ayahya.aesirr.myrestaurant.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import me.ayahya.aesirr.myrestaurant.Constants;
import me.ayahya.aesirr.myrestaurant.models.Restaurant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {
    private static final String TAG = "YelpService";

    public static void findRestaurants(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder yelpUrlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        yelpUrlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = yelpUrlBuilder.build().toString();

        Log.d(TAG, "yelpUrl" + url);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Constants.YELP_API_KEY)
                .build();
        Log.d(TAG, "testRequest:addHeader" + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Restaurant> processResults(Response response) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        try {
            String JSONData = response.body().string();
            JSONObject yelpJSON = new JSONObject(JSONData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");

            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject restaurantJSON = businessesJSON.getJSONObject(i);
                String name = restaurantJSON.getString("name");
                String phone = restaurantJSON.optString("display_phone", "Phone not available");
                String website = restaurantJSON.getString("url");
                double rating = restaurantJSON.getDouble("rating");

                String imageUrl = restaurantJSON.getString("image_url");

                double latitude = restaurantJSON.getJSONObject("coordinates").getDouble("latitude");
                double longitude = restaurantJSON.getJSONObject("coordinates").getDouble("longitude");

                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJSON = restaurantJSON.getJSONObject("location")
                        .getJSONArray("display_address");
                for (int y = 0; y < addressJSON.length(); y++) {
                    address.add(addressJSON.get(y).toString());
                }

                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoriesJSON = restaurantJSON.getJSONArray("categories");

                for (int x = 0; x < categoriesJSON.length(); x++) {
                    categories.add(categoriesJSON.getJSONObject(x).getString("title"));
                }

                Restaurant restaurant = new Restaurant(name, phone, website, rating,
                        imageUrl, address, latitude, longitude, categories);
                restaurants.add(restaurant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return restaurants;
    }
}
