package Exercicios.TabelaDispersao;

import Exercicios.ListaEncadeada.ListaEncadeada;
import Exercicios.ListaEncadeada.NoLista;

public class MapaDispersao<T> {

    /// {@link ListaEncadeada} de {@link NoMapa}
    private ListaEncadeada<NoMapa<T>>[] info;

    public MapaDispersao(int tamanho) {
        info = new ListaEncadeada[tamanho];
    }

    /// Calcula o valor do hash com base no tamanho da lista e da chave
    private int calcularHash(int chave) {
        int tamanho = info.length;
        return chave % tamanho;
    }

    /// Insere o valor
    public void inserir(int chave, T dado) {
        int indice = calcularHash(chave);

        if (info[indice] == null) {
            info[indice] = new ListaEncadeada<>();
        }

        NoMapa<T> noMapa = new NoMapa<>();
        noMapa.setChave(chave);
        noMapa.setInfo(dado);

        info[indice].inserir(noMapa);
    }

    /// Remove o valor presente na chave
    public void remover(int chave) {
        int indice = calcularHash(chave);

        if (info[indice] != null) {
            NoMapa<T> noMapa = new NoMapa<>();
            noMapa.setChave(chave);

            info[indice].retirar(noMapa);
        }
    }

    /// Busca o valor presente na chave
    public T buscar(int chave) {
        int indice = calcularHash(chave);

        if (info[indice] != null) {
            NoMapa<T> noMapa = new NoMapa<>();
            noMapa.setChave(chave);

            NoLista<NoMapa<T>> no = info[indice].buscar(noMapa);

            if (no != null) {
                return no.getInfo().getInfo();
            }
        }

        return null;
    }
}
