package ufba.contatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public ContatoDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        db = null;
    }

    public List<Contato> getAll() {
        List<Contato> lista = new ArrayList<>();

        Cursor cursor = db.query(false, "contato", null,
                null, null, null, null, "nome", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            String nome = cursor.getString(1);
            String telefone = cursor.getString(2);
            Contato contato = new Contato(id, nome, telefone);
            lista.add(contato);
            cursor.moveToNext();
        }

        return lista;
    }

    public void insert(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nome", contato.nome);
        cv.put("telefone", contato.telefone);
        long id = db.insert("contato", null, cv);
        Log.d("teste", "inserido contato com id = " + id);
    }

    public void remove(long id) {
        db.delete("contato", "_id = " + id, null);
    }

}
