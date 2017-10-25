package com.udemy.imageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean currImage = true;

    public void clickChangeBtn(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);

        if (currImage) {
            image.setImageResource(R.drawable.cat2);
            currImage = false;
        } else {
            image.setImageResource(R.drawable.cat1);
            currImage = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
