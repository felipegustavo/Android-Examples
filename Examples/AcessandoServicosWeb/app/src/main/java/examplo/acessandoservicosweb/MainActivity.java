package examplo.acessandoservicosweb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Sem conexão com a internet.", Toast.LENGTH_LONG).show();
            return;
        }

        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL("http://contatos-rest.herokuapp.com/contatos");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            Log.i("CONTATOS SERVICE", getString(httpURLConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getString(InputStream in) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

        StringBuilder str = new StringBuilder();
        String line = null;

        while ((line = buffer.readLine()) != null) {
            str.append(line);
        }

        return str.toString();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

}