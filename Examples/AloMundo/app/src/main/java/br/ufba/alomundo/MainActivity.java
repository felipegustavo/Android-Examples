package br.ufba.alomundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView viewTexto;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewTexto = (TextView)findViewById(R.id.text1);

        Log.i("info","onCreate");
    }

    public void clicouNoBotao(View v) {
        Log.i("info", "clicou no botao");

        Toast.makeText(this, "Clicou no botão", Toast.LENGTH_SHORT).show();

        contador++;
        viewTexto.setText("Clicou " + contador + " vezes");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("info","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("info","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("info","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("info","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("info","onDestroy");
    }
}
