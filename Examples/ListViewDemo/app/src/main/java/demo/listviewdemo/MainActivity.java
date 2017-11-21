package demo.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FilmeAdapter adapater;
    private Filme[] itens = {
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC"),
            new Filme("Os 3 porquinhos", 1970, "Steven Spielberg"),
            new Filme("Android", 2005, "Steve Jobs"),
            new Filme("Matematica", 2017, "DCC")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        adapater = new FilmeAdapter(this, android.R.layout.simple_list_item_2, itens);
        listView.setAdapter(adapater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme item = (Filme) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Clicou em " + item.titulo + " " + item.diretor + " " + item.anoLancamento,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
