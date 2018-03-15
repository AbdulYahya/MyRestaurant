package me.ayahya.aesirr.myrestaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.findRestaurantsButton) Button findRestaurantsButton;
    @BindView(R.id.locationEditText) EditText locationEditText;
    @BindView(R.id.mainHeaderTextView) TextView mainHeaderFontTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mainHeaderFontTextView.setTypeface(caviarDreamsFont);

        findRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == findRestaurantsButton) {
            String location = locationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
