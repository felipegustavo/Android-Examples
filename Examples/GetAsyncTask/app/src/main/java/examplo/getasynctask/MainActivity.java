package examplo.getasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class TestTask extends AsyncTask<Void, Void, String> {

        private String getString(InputStream in) throws IOException {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));

            StringBuilder str = new StringBuilder();
            String line = null;

            while ((line = buffer.readLine()) != null) {
                str.append(line);
            }

            return str.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            String html = "Teste";

            try {
                URL url = new URL("http://qualoperadora.info/consulta");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");

                urlConnection.setRequestProperty("Host", "qualoperadora.info");
                urlConnection.setRequestProperty("Referer", "http://qualoperadora.info/");

                urlConnection.connect();

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes("tel=71986764888");

                wr.flush();
                wr.close();

                html = getString(urlConnection.getInputStream());
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            return html;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("DEBUG", s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TestTask().execute();
    }

}