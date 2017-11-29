package examplo.alertdialogdemo;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView langText;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.english:
                changeLangage("English");
                break;
            case R.id.portuges:
                changeLangage("Português");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        langText = (TextView) findViewById(R.id.langText);

        if (!PreferenceManager.getDefaultSharedPreferences(this).contains("lang")) {
            new AlertDialog.Builder(this).
                    setIcon(android.R.drawable.ic_dialog_alert).
                    setTitle("Idioma").
                    setMessage("Selecione um idioma").
                    setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            changeLangage("English");
                        }
                    }).
                    setNegativeButton("Português", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            changeLangage("Português");
                        }
                    }).
                    show();
        } else {
            changeLangage(PreferenceManager.getDefaultSharedPreferences(this).getString("lang", ""));
        }
    }

    private void changeLangage(String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("lang", lang).apply();
        langText.setText(lang);
    }

}