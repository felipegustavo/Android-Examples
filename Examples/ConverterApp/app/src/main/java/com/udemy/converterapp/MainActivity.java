package com.udemy.converterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertValue(View view) {
        EditText valueField = (EditText) findViewById(R.id.valueTxtField);

        if (valueField.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please, inform some value before convet!!!", Toast.LENGTH_LONG).show();
        } else {
            float fvalue = Float.valueOf(valueField.getText().toString());
            Toast.makeText(MainActivity.this, "Value: " + fvalue * 3.12, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
