package com.udemy.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLoginBtn(View view) {
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText passwdField = (EditText) findViewById(R.id.passwdField);

        Log.i("INFO", String.format("Email: %s | Password: %s", emailField.getText().toString(), passwdField.getText().toString()));
    }
}
