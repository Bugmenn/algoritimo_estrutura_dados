package Exercicios.FilaListaEncadeada;

public class FilaLista<T> implements Fila<T>{

    private ListaEncadeada<T> lista = new ListaEncadeada<>();

    public FilaLista() {

    }

    @Override
    public void inserir(T valor) {
        lista.inserirNoFinal(valor);
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public T peek() {
        if (estaVazia())
            throw new RuntimeException("Fila Vazia");

        return lista.getPrimeiro().getInfo();
    }

    @Override
    public T retirar() {
        if (estaVazia())
            throw new RuntimeException("Fila Vazia");

        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public void liberar() {
        NoLista<T> p = lista.getPrimeiro();

        while (p != null) {
            retirar();
            p = lista.getPrimeiro();
        }
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
