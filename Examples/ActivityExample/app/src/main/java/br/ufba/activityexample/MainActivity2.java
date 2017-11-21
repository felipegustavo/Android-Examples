package br.ufba.activityexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);
    }

    public void submitMsg(View view) {
        Intent intent = getIntent();
        intent.putExtra("message", editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
