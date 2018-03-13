package me.ayahya.aesirr.myrestaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText locationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button findRestaurantButton;
        TextView fontTextView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fontTextView = findViewById(R.id.mainHeaderTextView);
        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        fontTextView.setTypeface(caviarDreamsFont);

        locationEditText = findViewById(R.id.locationEditText);
        findRestaurantButton = findViewById(R.id.findRestaurantsButton);

        findRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = locationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
