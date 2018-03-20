package me.ayahya.aesirr.myrestaurant;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
                .header("Authorization", Constants.YELP_API_KEY)
                .build();
        Log.d(TAG, "Request:header" + request);

        Request testRequest = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Constants.YELP_API_KEY)
                .build();
        Log.d(TAG, "testRequest:addHeader" + testRequest);

        Call call = client.newCall(request);
        call.enqueue(callback);

        Call testCall = client.newCall(testRequest);
        testCall.enqueue(callback);
    }
}
