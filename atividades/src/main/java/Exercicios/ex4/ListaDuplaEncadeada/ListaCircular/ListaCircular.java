package Exercicios.ex4.ListaDuplaEncadeada.ListaCircular;

import Exercicios.ex3.ListaEncadeada.NoLista;

public class ListaCircular<T> {

    /// Nó inicial
    private NoLista<T> primeiro;
    /// Último nó no qual aponta para o primeiro
    private NoLista<T> ultimo;

    public ListaCircular() {
        primeiro = null;
        ultimo = null;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void inserir(T dado) {
        NoLista<T> novoNo = new NoLista<>();
        novoNo.setInfo(dado);

        // se a lista estiver vazia, o novo nó será o primeiro e o último
        if (estaVazia()) {
            primeiro = novoNo;
            ultimo = novoNo;
            novoNo.setProximo(primeiro); // aponta para ele mesmo para manter a circularidade
        } else {
            // se a lista não estiver vazia, adiciona o novo nó no final
            ultimo.setProximo(novoNo); // o último nó aponta para o novo nó
            ultimo = novoNo; // atualiza o último nó para o novo nó
            ultimo.setProximo(primeiro); // mantém a circularidade apontando para o primeiro
        }
    }

    public void remover(T dado) {
        if (estaVazia())
            return;

        NoLista<T> atual = primeiro;
        NoLista<T> anterior = null;
        do {
            if (atual.getInfo() == dado) { // se encontrar o nó com o valor desejado
                if (atual == primeiro) {
                    // se for o primeiro, move o primeiro para o próximo nó
                    primeiro = primeiro.getProximo();
                    ultimo.setProximo(primeiro); // mantém a circularidade
                } else if (atual == ultimo) {
                    // se for o último, atualiza o último para o nó anterior
                    ultimo = anterior;
                    ultimo.setProximo(primeiro); // mantém a circularidade
                } else {
                    // nó intermediário: remove ajustando o ponteiro do nó anterior
                    anterior.setProximo(atual.getProximo());
                }
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        } while (atual != primeiro); // percorre a lista até voltar ao início

        return;
    }

    /// Busca um valor dentro dos nós
    /// @return o valor caso encontrado, caso não retorna null
    public NoLista<T> buscar(T info) {
        NoLista<T> p = primeiro;

        do {
            if (p.getInfo().equals(info))
                return p;

            p = p.getProximo();
        } while (p != primeiro);

        return null;
    }

    public void exibir() {
        if (estaVazia()) {
            System.out.println("Lista vazia");
            return;
        }

        NoLista<T> atual = primeiro;
        do {
            System.out.print(atual.getInfo() + " -> ");
            atual = atual.getProximo();
        } while (atual != primeiro);
        System.out.println("(volta ao início)");
    }
}
