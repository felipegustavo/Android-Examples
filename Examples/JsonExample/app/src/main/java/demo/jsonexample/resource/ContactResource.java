package demo.jsonexample.resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import demo.jsonexample.domain.Contact;

public class ContactResource {

    private static final String SERVICE = "http://contatos-rest.herokuapp.com/contatos";

    public long insertContact(Contact contact) throws JSONException {
        JSONObject json = toJSON(contact);
        String result = postUrl(SERVICE, json.toString());

        JSONObject response = new JSONObject(result);
        return response.getLong("id");
    }

    public List<Contact> getContacts() throws JSONException {
        List<Contact> contacts = new ArrayList<>();

        String result = getUrl();
        JSONArray array = new JSONArray(result);

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            Contact contact = fromJSON(object);
            contacts.add(contact);
        }

        return contacts;
    }

    private Contact fromJSON(JSONObject object) throws JSONException {
        Contact contact = new Contact(-1L, object.getString("nome"), object.getString("telefone"));
        if (object.has("id")) {
            contact.setId(object.getLong("id"));
        }
        return contact;
    }

    private JSONObject toJSON(Contact contact) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("nome", contact.getName());
        object.put("telefone", contact.getTelephone());
        return object;
    }

    private String postUrl(String urlStr, String body) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            if (urlConnection.getResponseCode() == 201) {
                result = readBody(urlConnection.getInputStream());
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getUrl() {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(SERVICE);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();

            result = readBody(in);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readBody(InputStream in) {
        InputStreamReader reader = new InputStreamReader(in);
        StringBuilder builder = new StringBuilder();

        try {
            int data = reader.read();

            while (data != -1) {
                builder.append((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

}