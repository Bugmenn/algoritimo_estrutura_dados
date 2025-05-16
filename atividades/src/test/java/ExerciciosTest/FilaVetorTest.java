package ExerciciosTest;

import Exercicios.FilaVetor.FilaCheiaException;
import Exercicios.FilaVetor.FilaVaziaException;
import Exercicios.FilaVetor.FilaVetor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilaVetorTest {

    @Test
    void testeEstaVazia() {
        FilaVetor<Integer> fila = new FilaVetor<>(10);
        assertTrue(fila.estaVazia());
    }

    @Test
    void testeEstaNaoVazia() {
        FilaVetor<Integer> fila = new FilaVetor<>(5);
        fila.inserir(10);
        assertFalse(fila.estaVazia());
    }

    @Test
    void testeDadosInfileirados() {
        FilaVetor<Integer> fila = new FilaVetor<>(10);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(10, fila.retirar());
        assertEquals(20, fila.retirar());
        assertEquals(30, fila.retirar());
        assertTrue(fila.estaVazia());
    }

    @Test
    void testeFilaCheiaException() {
        FilaVetor<Integer> fila = new FilaVetor<>(3);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertThrows(FilaCheiaException.class, () -> fila.inserir(40));
    }

    @Test
    void testeFilaVaziaException() {
        FilaVetor<Integer> fila = new FilaVetor<>(3);
        assertThrows(FilaVaziaException.class, () -> fila.retirar());
    }

    @Test
    void testePeek() {
        FilaVetor<Integer> fila = new FilaVetor<>(5);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(10, fila.peek());
        assertEquals(10, fila.retirar());
    }

    @Test
    void testeLiberar() {
        FilaVetor<Integer> fila = new FilaVetor<>(5);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        fila.liberar();
        assertTrue(fila.estaVazia());
    }

    @Test
    void testeConcatenacao() {
        FilaVetor<Integer> fila1 = new FilaVetor<>(5);
        fila1.inserir(10);
        fila1.inserir(20);
        fila1.inserir(30);

        FilaVetor<Integer> fila2 = new FilaVetor<>(3);
        fila2.inserir(40);
        fila2.inserir(50);

        FilaVetor<Integer> fila3 = fila1.criarFilaConcatenada(fila2);
        assertEquals("10, 20, 30", fila1.toString());
        assertEquals("40, 50", fila2.toString());
        assertEquals("10, 20, 30, 40, 50", fila3.toString());
        assertEquals(8, fila3.getLimite());
    }
}