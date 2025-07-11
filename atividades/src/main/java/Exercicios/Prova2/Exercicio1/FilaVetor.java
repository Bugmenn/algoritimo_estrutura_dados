package Exercicios.Prova2.Exercicio1;

import Exercicios.ex6.FilaVetor.Fila;
import Exercicios.ex6.FilaVetor.FilaCheiaException;
import Exercicios.ex6.FilaVetor.FilaVaziaException;

public class FilaVetor<T> implements Fila<T> {

    private T[] info;
    private int limite;
    private int tamanho;
    private int inicio;

    public FilaVetor(int limite) {
        info = (T[]) new Object[limite];
        this.limite = limite;
        tamanho = 0;
        inicio = 0;
    }

    @Override
    public void inserir(T valor) {
        if (tamanho == limite) {
            throw new FilaCheiaException("Fila está cheia");
        }

        int posicaoInserir = (inicio + tamanho) % limite;
        info[posicaoInserir] = valor;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /// Pega dado armazenado na posição inicio
    /// @return Object
    @Override
    public T peek() {
        if (estaVazia()) {
            throw new FilaVaziaException("Fila está vazia");
        }

        return info[inicio];
    }

    @Override
    public T retirar() {
        T valor = peek();
        inicio = (inicio + 1) % limite;
        tamanho--;
        return valor;
    }

    @Override
    public void liberar() {
        while (tamanho != 0)
            retirar();
    }

    public FilaVetor<T> criarFilaConcatenada(FilaVetor<T> f2) {
        int tamanho = f2.getLimite() + this.limite;
        FilaVetor<T> f3 = new FilaVetor<>(tamanho);

        // Inserir elementos da fila atual
        for (int i = 0; i < this.tamanho; i++) {
            int indice = (this.inicio + i) % this.limite;
            f3.inserir(this.info[indice]);
        }

        // Inserir elementos da fila f2
        for (int i = 0; i < f2.tamanho; i++) {
            int indice = (f2.inicio + i) % f2.limite;
            f3.inserir(f2.info[indice]);
        }

        return f3;
    }

    @Override
    public String toString() {
        if (estaVazia()) {
            return "";
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int indice = (inicio + i) % limite;
            texto.append(info[indice]);

            if (i < tamanho - 1) {
                texto.append(", ");
            }
        }

        return texto.toString();
    }

    public int getLimite() {
        return limite;
    }

    public void encolher() {
        T[] novoInfo = (T[]) new Object[tamanho];

        for (int i = 0; i < tamanho; i++) {
            int index = (inicio + i) % limite;
            novoInfo[i] = info[index];
        }

        info = novoInfo;
        limite = tamanho;
        inicio = 0;
    }

    public static void main(String[] args) {
        FilaVetor<String> filaVetor = new FilaVetor<>(10);

        filaVetor.inserir("A");
        filaVetor.retirar();
        filaVetor.inserir("A");
        filaVetor.retirar();
        filaVetor.inserir("A");
        filaVetor.retirar();

        filaVetor.inserir("A");
        filaVetor.inserir("B");
        filaVetor.inserir("C");
        filaVetor.inserir("D");

        filaVetor.encolher();
    }
}
