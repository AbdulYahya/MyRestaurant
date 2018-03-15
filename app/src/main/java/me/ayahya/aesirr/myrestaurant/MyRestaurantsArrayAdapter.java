package me.ayahya.aesirr.myrestaurant;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRestaurantsArrayAdapter extends ArrayAdapter{
    private Context context;
    private String[] restaurants;
    private String[] cuisines;

    public MyRestaurantsArrayAdapter(Context context, int resource, String[] restaurants, String[] cuisines) {
        super(context, resource);
        this.context = context;
        this.restaurants = restaurants;
        this.cuisines = cuisines;
    }

    @Override
    public Object getItem(int position) {
        String restaurant = restaurants[position];
        String cuisine = cuisines[position];
        return String.format("%s \nServes great: %s", restaurant, cuisine);
    }

    @Override
    public int getCount() {
        return restaurants.length;
    }
}
