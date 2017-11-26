package demo.jsonexample.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import demo.jsonexample.R;
import demo.jsonexample.domain.Contact;
import demo.jsonexample.resource.ContactResource;

public class Main2Activity extends AppCompatActivity {

    private Button viewBtn;
    private Button sendBtn;
    private EditText nameEditTxt;
    private EditText phoneEditTxt;

    private Context context;

    class SendTask extends AsyncTask<String, Void, Long> {

        @Override
        protected Long doInBackground(String... params) {
            Contact contact = new Contact(-1, params[0], params[1]);
            ContactResource resource = new ContactResource();

            try {
                return resource.insertContact(contact);
            } catch (JSONException e) {
                e.printStackTrace();
                return -1L;
            }

        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);

            if (id > 0) {
                Toast.makeText(context, "Inserido novo usuário com id " + id, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Houve um erro ao inserir um novo usuário.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this.getApplicationContext();

        viewBtn = (Button) findViewById(R.id.viewBtn);
        sendBtn = (Button) findViewById(R.id.sendBtn);

        nameEditTxt = (EditText) findViewById(R.id.nameEditTxt);
        phoneEditTxt = (EditText) findViewById(R.id.phoneEditTxt);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(context, "Sem conexão com a internet", Toast.LENGTH_LONG).show();
            sendBtn.setVisibility(View.GONE);
        }
    }

    public void viewContacts(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendContact(View view) {
        String name = nameEditTxt.getText().toString();
        String phone = phoneEditTxt.getText().toString();

        SendTask task = new SendTask();
        task.execute(name, phone);
    }

}