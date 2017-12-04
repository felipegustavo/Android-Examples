package ufba.contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContatoDao contatoDao;

    private TextView textContatos;
    private EditText editNome;
    private EditText editTelefone;
    private Button buttonInserir;
    private Button buttonRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textContatos = (TextView)findViewById(R.id.textContatos);
        editNome = (EditText)findViewById(R.id.editNome);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        buttonInserir = (Button)findViewById(R.id.buttonInserir);
        buttonRemover = (Button)findViewById(R.id.buttonRemover);
        buttonInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserirContato();
            }
        });
        buttonRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerPrimeiroContato();
            }
        });

        contatoDao = new ContatoDao(this);
    }

    private void removerPrimeiroContato() {
        List<Contato> contatos = contatoDao.getAll();
        if (contatos != null && contatos.size() >= 1) {
            contatoDao.remove(contatos.get(0).id);
            atualizaListaDeContatos();
        }
    }

    public void inserirContato() {
        long id = 0;
        String nome = editNome.getText().toString();
        String telefone = editTelefone.getText().toString();

        Contato contato = new Contato(id, nome, telefone);

        contatoDao.insert(contato);
        atualizaListaDeContatos();
    }

    private void atualizaListaDeContatos() {
        List<Contato> contatos = contatoDao.getAll();

        String s = "Lista de contatos\n";
        for (Contato contato : contatos) {
            s += contato.nome + " - " + contato.telefone + "\n";
        }
        textContatos.setText(s);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contatoDao.open();
        atualizaListaDeContatos();
    }

    @Override
    protected void onDestroy() {
        contatoDao.close();
        super.onDestroy();
    }

}