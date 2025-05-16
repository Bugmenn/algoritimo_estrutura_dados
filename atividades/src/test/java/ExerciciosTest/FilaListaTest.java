package ExerciciosTest;

import Exercicios.FilaListaEncadeada.FilaLista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilaListaTest {

    @Test
    void testeEstaVazia() {
        FilaLista<Integer> fila = new FilaLista<>();
        assertTrue(fila.estaVazia());
    }

    @Test
    void testeEstaNaoVazia() {
        FilaLista<Integer> fila = new FilaLista<>();
        fila.inserir(10);
        assertFalse(fila.estaVazia());
    }

    @Test
    void testeEnfileiramento() {
        FilaLista<Integer> fila = new FilaLista<>();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(10, fila.retirar());
        assertEquals(20, fila.retirar());
        assertEquals(30, fila.retirar());
        assertTrue(fila.estaVazia());
    }

    @Test
    void testePeek() {
        FilaLista<Integer> fila = new FilaLista<>();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(10, fila.peek());
        assertEquals(10, fila.retirar());
    }

    @Test
    void testeLiberar() {
        FilaLista<Integer> fila = new FilaLista<>();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        fila.liberar();
        assertTrue(fila.estaVazia());
    }
}