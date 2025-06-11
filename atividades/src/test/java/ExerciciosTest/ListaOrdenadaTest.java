package ExerciciosTest;

import Exercicios.Busca.ListaOrdenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaOrdenadaTest {

    @Test
    void inserirTest() {
        ListaOrdenada<Integer> listaOrdenada = new ListaOrdenada<>();

        listaOrdenada.inserir(100);
        listaOrdenada.inserir(20);
        listaOrdenada.inserir(70);
        listaOrdenada.inserir(1);

        assertEquals("1,20,70,100", listaOrdenada.toString());
    }

    private ListaOrdenada<Integer> listaOrdenada;

    @BeforeEach
    void criarListaComValoresPadrao() {
        listaOrdenada = new ListaOrdenada<>();
        int[] valores = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        for (int valor : valores) {
            listaOrdenada.inserir(valor);
        }
    }

    @Test
    void buscar20Test() {
        assertEquals(2, listaOrdenada.buscar(20));
    }

    @Test
    void buscar40Test() {
        assertEquals(4, listaOrdenada.buscar(40));
    }

    @Test
    void buscar70Test() {
        assertEquals(7, listaOrdenada.buscar(70));
    }

    @Test
    void buscar100Test() {
        assertEquals(10, listaOrdenada.buscar(100));
    }

    @Test
    void buscar85Test() {
        assertEquals(-1, listaOrdenada.buscar(85));
    }
}