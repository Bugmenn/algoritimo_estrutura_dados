package ExerciciosTest;

import Exercicios.ex7.ArvoreBinaria.ArvoreBinaria;
import Exercicios.ex7.ArvoreBinaria.NoArvoreBinaria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArvoreBinariaTest {

    @Test
    void testIsVazio() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        assertTrue(arvore.estaVazia());
    }

    @Test
    void testIsNotVazio() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        arvore.setRaiz(new NoArvoreBinaria<>(5));
        assertFalse(arvore.estaVazia());
    }

    @Test
    void testPreOrdem() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertEquals("<1<2<><4<><>>><3<5<><>><6<><>>>>", arvore.toString());
    }

    @Test
    void testPertenceRaiz() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertTrue(arvore.pertence(1));
    }

    @Test
    void testPertenceNo() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertTrue(arvore.pertence(3));
    }

    @Test
    void testPertenceNo2() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertTrue(arvore.pertence(6));
    }

    @Test
    void testNaoPertenceNo() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertFalse(arvore.pertence(10));
    }

    @Test
    void testQuantidadeNo() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        assertEquals(6, arvore.contarNos());
    }
}