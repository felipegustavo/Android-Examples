package demo.jsonexample.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import demo.jsonexample.domain.Contact;

public class ContactDAO extends SQLiteDBHelper {

    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TELEPHONE = "telephone";

    public static final String CREATE_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY " +
            "KEY, %s TEXT, %s TEXT);", TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_TELEPHONE);

    public ContactDAO(Context context) {
        super(context);
    }

    public long insert(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, contact.getId());
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_TELEPHONE, contact.getTelephone());
        return database.insert(TABLE_NAME, null, values);
    }

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_TELEPHONE},
                null, null, null, null, null);

        // Or just do this, does the same thing
        // Cursor cursor1 = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            Contact contact = new Contact(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
            contacts.add(contact);
        }

        return contacts;
    }

    public int deleteAll() {
        return database.delete(TABLE_NAME, null, null);
    }

}