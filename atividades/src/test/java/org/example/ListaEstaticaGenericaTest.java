package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEstaticaGenericaTest {

    @Test
    void inverterComDadosPar() {
        ListaEstaticaGenerica<Integer> lista = new ListaEstaticaGenerica<Integer>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.inverter();
        assertEquals("20,15,10,5", lista.toString());
    }

    @Test
    void inverterComDadosImpar() {
        ListaEstaticaGenerica<Integer> lista = new ListaEstaticaGenerica<Integer>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.inserir(25);
        lista.inverter();
        assertEquals("25,20,15,10,5", lista.toString());
    }
}