package demo.jsonexample.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.List;

import demo.jsonexample.R;
import demo.jsonexample.adapter.ContactAdapter;
import demo.jsonexample.domain.Contact;
import demo.jsonexample.persistence.ContactDAO;
import demo.jsonexample.resource.ContactResource;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Context context;

    class ServiceTask extends AsyncTask<Void, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(Void... params) {
            ContactResource resource = new ContactResource();
            try {
                return resource.getContacts();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            ContactDAO dao = new ContactDAO(context);

            for (Contact c : contacts) {
                dao.insert(c);
            }

            ContactAdapter adapter = new ContactAdapter(context, contacts);
            listView.setAdapter(adapter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();

        listView = (ListView) findViewById(R.id.listView);

        ServiceTask serviceTask = new ServiceTask();
        serviceTask.execute();

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            ContactDAO dao = new ContactDAO(context);
            ContactAdapter adapter = new ContactAdapter(context, dao.getAll());
            listView.setAdapter(adapter);

        }
    }

}