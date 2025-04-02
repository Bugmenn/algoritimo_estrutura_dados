package ExerciciosTest;

import Exercicios.ListaEncadeada.ListaEncadeada;
import Exercicios.ListaEncadeada.NoLista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListaEncadeadaTest {

    @Test
    void isListaVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        assertTrue(lista.estaVazia());
    }

    @Test
    void isNotListaVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        assertFalse(lista.estaVazia());
    }

    @Test
    void inserirUmValor() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);

        NoLista<Integer> primeiroNo = lista.getPrimeiro();

        assertNotNull(primeiroNo);
        assertEquals(5, primeiroNo.getInfo());

        assertNull(primeiroNo.getProximo());
    }

    @Test
    void inserirTresValores() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);

        NoLista<Integer> primeiroNo = lista.getPrimeiro();
        NoLista<Integer> segundoNo = primeiroNo.getProximo();
        NoLista<Integer> terceiroNo = segundoNo.getProximo();

        assertEquals(15, primeiroNo.getInfo());
        assertEquals(10, segundoNo.getInfo());
        assertEquals(5, terceiroNo.getInfo());

        assertNull(terceiroNo.getProximo());
    }

    @Test
    void buscarPrimeiraPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(20, lista.buscar(20).getInfo());
    }

    @Test
    void buscarMeioLista() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(15, lista.buscar(15).getInfo());
    }

    @Test
    void buscarInexistente() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertNull(lista.buscar(50));
    }

    @Test
    void excluirPrimeiraPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(20);

        NoLista<Integer> primeiroNo = lista.getPrimeiro();

        assertNotNull(primeiroNo);
        assertEquals(15, primeiroNo.getInfo());

        NoLista<Integer> segundoNo = primeiroNo.getProximo();
        assertNotNull(segundoNo);
        assertEquals(10, segundoNo.getInfo());

        NoLista<Integer> terceiroNo = segundoNo.getProximo();
        assertNotNull(terceiroNo);
        assertEquals(5, terceiroNo.getInfo());

        assertNull(terceiroNo.getProximo());
    }

    @Test
    public void testRemoverElementoDoMeio() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(15);

        NoLista<Integer> primeiroNo = lista.getPrimeiro();

        assertNotNull(primeiroNo);
        assertEquals(20, primeiroNo.getInfo());

        NoLista<Integer> segundoNo = primeiroNo.getProximo();
        assertNotNull(segundoNo);
        assertEquals(10, segundoNo.getInfo());

        NoLista<Integer> terceiroNo = segundoNo.getProximo();
        assertNotNull(terceiroNo);
        assertEquals(5, terceiroNo.getInfo());

        assertNull(terceiroNo.getProximo());
    }

    @Test
    public void testObterNoPosicaoZero() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> noPosicaoZero = lista.obterNo(0);

        assertNotNull(noPosicaoZero);
        assertEquals(20, noPosicaoZero.getInfo());
    }

    @Test
    public void testObterNoUltimaPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> noUltimaPosicao = lista.obterNo(3);

        assertNotNull(noUltimaPosicao);
        assertEquals(5, noUltimaPosicao.getInfo());
    }

    @Test
    public void testObterNoPosicaoInvalida() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        // Adicionar os números 5, 10, 15 e 20 (nessa ordem)
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20); // 20 será o primeiro elemento da lista

        // Testar se obterNo(10) lança uma exceção
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.obterNo(10);
        });
    }

    @Test
    public void testObterComprimentoListaVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        int comprimento = lista.obterComprimento();

        assertEquals(0, comprimento);
    }

    @Test
    public void testObterComprimentoListaNaoVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        int comprimento = lista.obterComprimento();

        assertEquals(4, comprimento);
    }
}