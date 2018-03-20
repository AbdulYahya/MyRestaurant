package me.ayahya.aesirr.myrestaurant;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRestaurantsArrayAdapter extends ArrayAdapter{
    private Context context;
    private Restaurant[] restaurants;

    public MyRestaurantsArrayAdapter(Context context, int resource, Restaurant[] restaurants) {
        super(context, resource);
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public Object getItem(int position) {
        Restaurant restaurant = restaurants[position];
        return String.format("%s", restaurant);
    }

    @Override
    public int getCount() {
        return restaurants.length;
    }
}
