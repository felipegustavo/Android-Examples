package examplo.acessandoservicosweb2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class DownloaderTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                Log.i("Resultado", ""+httpURLConnection.getResponseCode());
                String json = getString(httpURLConnection.getInputStream());
                return new JSONArray(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Log.i("Info", "id = " + obj.getInt("id") + ", nome = " + obj.getString("nome") +
                            ", telefone = " + obj.getString("telefone"));
                }
            } catch (JSONException e) {
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloaderTask task = new DownloaderTask();
        task.execute("http://contatos-rest.herokuapp.com/contatos");
    }
}