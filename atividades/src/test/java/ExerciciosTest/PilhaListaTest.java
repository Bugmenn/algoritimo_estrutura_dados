package ExerciciosTest;

import Exercicios.PilhaLista.PilhaLista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PilhaListaTest {

    @Test
    public void estaVazia() {
        PilhaLista<Integer> lista = new PilhaLista<>();
        assertTrue(lista.estaVazia());
    }

    @Test
    public void naoEstaVazia() {
        PilhaLista<Integer> lista = new PilhaLista<>();
        lista.push(10);
        assertFalse(lista.estaVazia());
    }

    @Test
    public void desempilhar() {
        PilhaLista<Integer> lista = new PilhaLista<>();
        lista.push(10);
        lista.push(20);
        lista.push(30);
        assertEquals(30, lista.pop());
        assertEquals(20, lista.pop());
        assertEquals(10, lista.pop());
        assertTrue(lista.estaVazia());
    }

    @Test
    public void peekTeste() {
        PilhaLista<Integer> lista = new PilhaLista<>();
        lista.push(10);
        lista.push(20);
        lista.push(30);
        assertEquals(30, lista.peek());
        lista.pop();
        assertEquals(20, lista.peek());
    }

    @Test
    public void liberarTeste() {
        PilhaLista<Integer> lista = new PilhaLista<>();
        lista.push(10);
        lista.push(20);
        lista.push(30);
        lista.liberar();
        assertTrue(lista.estaVazia());
    }
}