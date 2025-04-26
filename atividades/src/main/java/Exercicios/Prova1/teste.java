package Exercicios.Prova1;

import Exercicios.Prova1.ListaDuplaEncadeada.ListaDupla;
import Exercicios.Prova1.ListaEncadeada.ListaEncadeada;

public class teste {
    public static void main(String[] args) {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(70);
        lista.inserir(60);
        lista.inserir(50);
        lista.inserir(40);
        lista.inserir(30);
        lista.inserir(20);
        lista.inserir(10);

        ListaEncadeada<Integer> novaLista;
        novaLista = lista.criarSublista(2,5);
        System.out.println(novaLista.toString());

        ListaDupla<String> listaDupla = new ListaDupla<>();
        listaDupla.inserir("A");
        listaDupla.inserir("B");
        listaDupla.inserir("C");
        listaDupla.inserir("D");
        ListaDupla<String> novaListaDupla = listaDupla.clonar();
        System.out.println("\n"+listaDupla.toString()+"\n"+novaListaDupla.toString());
    }
}
