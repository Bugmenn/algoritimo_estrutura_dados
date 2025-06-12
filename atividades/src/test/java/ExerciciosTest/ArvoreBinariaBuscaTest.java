package ExerciciosTest;

import Exercicios.ArvoreBinariaBusca.ArvoreBinariaBusca;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ArvoreBinariaBuscaTest {

    @Test
    void inserirTest() {
        int[] listaValores = { 50, 30, 70, 40, 25, 75, 65, 35, 60 };

        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();

        for (int valor : listaValores) {
            arvore.inserir(valor);
        }

        assertEquals("<50<30<25<><>><40<35<><>><>>><70<65<60<><>><>><75<><>>>>", arvore.toString());
    }

    @Test
    void removerFolha() {
        int[] listaValores = { 50, 30, 25, 40 };

        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();

        for (int valor : listaValores) {
            arvore.inserir(valor);
        }

        arvore.retirar(40);

        assertEquals("<50<30<25<><>><>><>>", arvore.toString());
    }

    @Test
    void removerNoComFilho() {
        int[] listaValores = { 80, 52, 90, 48, 71, 63, 67 };

        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();

        for (int valor : listaValores) {
            arvore.inserir(valor);
        }

        arvore.retirar(71);

        assertEquals("<80<52<48<><>><63<><67<><>>>><90<><>>>", arvore.toString());
    }

    @Test
    void removerNoComDoisFilhos() {
        int[] listaValores = { 250,38,26,72,55,90,41,60,43,78, 92,74 };

        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();

        for (int valor : listaValores) {
            arvore.inserir(valor);
        }

        arvore.retirar(38);

        assertEquals("<250<41<26<><>><72<55<43<><>><60<><>>><90<78<74<><>><>><92<><>>>>><>>", arvore.toString());
    }
}