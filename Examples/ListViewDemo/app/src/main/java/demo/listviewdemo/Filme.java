package demo.listviewdemo;

/**
 * Created by felipe on 11/8/17.
 */

public class Filme {

    public String titulo;
    public int anoLancamento;
    public String diretor;

    public Filme(String titulo, int anoLancamento, String diretor) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.diretor = diretor;
    }

    @Override
    public String toString() {
        return titulo;
    }

}
