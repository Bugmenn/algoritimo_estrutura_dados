package ExerciciosTest;

import Exercicios.ListaDuplaEncadeada.ListaDupla;
import Exercicios.ListaDuplaEncadeada.NoListaDupla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaDuplaTest {

    @Test
    void inclusaoTest() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        System.out.println("Percorrendo a lista do primeiro ao último nó:");
        NoListaDupla<Integer> p = lista.getPrimeiro();
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getProximo();
        }
        System.out.println();

        System.out.println("Percorrendo a lista do último ao primeiro nó:");
        p = lista.getPrimeiro();

        // encontrando o ultimo nó
        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getAnterior();
        }
        System.out.println();
    }

    @Test
    void buscarInicio() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(20, lista.buscar(20).getInfo());
    }

    @Test
    void buscarMeio() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(10, lista.buscar(10).getInfo());
    }

    @Test
    void removerInicio() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(20);

        System.out.println("Percorrendo a lista do primeiro ao último nó:");
        NoListaDupla<Integer> p = lista.getPrimeiro();
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getProximo();
        }
        System.out.println();

        System.out.println("Percorrendo a lista do último ao primeiro nó:");
        p = lista.getPrimeiro();

        // encontrando o ultimo nó
        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getAnterior();
        }
        System.out.println();
    }

    @Test
    void removerMeio() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(10);

        System.out.println("Percorrendo a lista do primeiro ao último nó:");
        NoListaDupla<Integer> p = lista.getPrimeiro();
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getProximo();
        }
        System.out.println();

        System.out.println("Percorrendo a lista do último ao primeiro nó:");
        p = lista.getPrimeiro();

        // encontrando o ultimo nó
        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getAnterior();
        }
        System.out.println();
    }

    @Test
    void removerFinal() {
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(5);

        System.out.println("Percorrendo a lista do primeiro ao último nó:");
        NoListaDupla<Integer> p = lista.getPrimeiro();
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getProximo();
        }
        System.out.println();

        System.out.println("Percorrendo a lista do último ao primeiro nó:");
        p = lista.getPrimeiro();

        // encontrando o ultimo nó
        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getAnterior();
        }
        System.out.println();
    }

    @Test
    void liberarTest(){
        ListaDupla<Integer> lista = new ListaDupla<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoListaDupla<Integer> no5 = lista.buscar(5);
        NoListaDupla<Integer> no10 = lista.buscar(10);
        NoListaDupla<Integer> no15 = lista.buscar(15);
        NoListaDupla<Integer> no20 = lista.buscar(20);

        lista.liberar();

        System.out.println("Verificando referências após liberação:");
        verificarNo(no5, 5);
        verificarNo(no10, 10);
        verificarNo(no15, 15);
        verificarNo(no20, 20);
    }

    private static void verificarNo(NoListaDupla<Integer> no, int valor) {
        if (no != null) {
            System.out.println("Nó " + valor + " - Anterior: " + no.getAnterior() + ", Próximo: " + no.getProximo());
        } else {
            System.out.println("Nó " + valor + " não encontrado.");
        }
    }
}