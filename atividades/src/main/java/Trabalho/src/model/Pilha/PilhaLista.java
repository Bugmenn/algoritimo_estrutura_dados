package Trabalho.src.model.Pilha;

import Trabalho.src.model.Exceptions.PilhaVaziaException;
import Trabalho.src.model.Interfaces.IPilha;
import Trabalho.src.model.Lista.ListaEncadeada;

public class PilhaLista<T> implements IPilha<T> {

    private final ListaEncadeada<T> lista = new ListaEncadeada<>();

    /// Insere o dado
    @Override
    public void push(T info) {
        lista.inserir(info);
    }

    /// Retira e retorna o dado do topo
    /// @return tipo {@code T}
    @Override
    public T pop() {
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    /// Pega o dado do topo
    @Override
    public T peek() {
        if(estaVazia())
            throw new PilhaVaziaException("Pilha cheia");

        return lista.getPrimeiro().getInfo();
    }

    /// Verifica se a pilha está vazia
    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    /// Limpa a pilha
    @Override
    public void liberar() {
        while (lista.getPrimeiro() != null) {
            pop();
        }
    }

    /// Transforma a pilha em string
    /// @return String
    @Override
    public String toString(){
        return lista.toString();
    }
}