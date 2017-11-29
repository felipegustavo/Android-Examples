package examplo.acessandoservicosweb4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    class DownloadTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL url = new URL("https://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73751/world.topo.bathy.200407.3x21600x10800.jpg");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                cancel(true);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.i("DEBUG", "A task terminou.");
            if (bitmap == null) {
                ImageView img = (ImageView) findViewById(R.id.imageView);
                img.setImageBitmap(bitmap);
            }
        }

        @Override
        protected void onCancelled() {
            Log.i("DEBUG", "A task foi cancelada.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
