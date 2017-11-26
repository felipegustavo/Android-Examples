package examplo.acessandoservicosweb3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class SendTask extends AsyncTask<JSONObject, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            Log.i("INFO", "Começando a enviar contatos.");
        }

        @Override
        protected Integer doInBackground(JSONObject... params) {
            int i = 0;
            try {
                for (i = 0; i < params.length; i++) {
                    URL url = new URL("http://contatos-rest.herokuapp.com/contatos");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.connect();

                    DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                    wr.writeBytes(params[0].toString());
                    wr.flush();
                    wr.close();

                    if (urlConnection.getResponseCode() == 201) {
                        publishProgress(i+1);
                    }
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("INFO", values[0] + " contatos enviados.");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Log.i("INFO", "Foram enviados " + integer + " contatos.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONObject obj1 = new JSONObject();
            obj1.put("nome", "Felipe");
            obj1.put("telefone", "1111-1111");

            JSONObject obj2 = new JSONObject();
            obj2.put("nome", "Rodrigo");
            obj2.put("telefone", "1111-1111");

            SendTask task = new SendTask();
            task.execute(obj1, obj2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
