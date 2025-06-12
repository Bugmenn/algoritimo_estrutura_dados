package Exercicios.Prova2;

import Exercicios.FilaListaEncadeada.Fila;
import Exercicios.FilaListaEncadeada.ListaEncadeada;
import Exercicios.FilaListaEncadeada.NoLista;

public class FilaLista<T> implements Fila<T> {

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

    private int duplicarElementos() {
        int quantidade = 0;
        int tamanhoOriginal = lista.obterComprimento();

        for (int i = 0; i < tamanhoOriginal; i++) {
            T valor = retirar();
            inserir(valor);
            inserir(valor);
            quantidade += 2;
        }

        return quantidade;
    }

    public static void main(String[] args) {
        FilaLista<Integer> fila = new FilaLista<>();

        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        int tamanho = fila.duplicarElementos();
    }
}
