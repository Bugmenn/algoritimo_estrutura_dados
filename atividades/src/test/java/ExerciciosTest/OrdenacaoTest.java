package ExerciciosTest;

import Exercicios.ex12.Ordenacao.OrdenacaoBolha;
import Exercicios.ex12.Ordenacao.OrdenacaoBolhaOtimizada;
import Exercicios.ex12.Ordenacao.OrdenacaoMergeSort;
import Trabalho.src.model.Ordenacao.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrdenacaoTest {

    private static final Integer[] vetorNaoOrdenado = { 70, 2, 88, 15, 90, 30 };
    private static final Integer[] vetorOrdenado = { 2, 15, 30, 70, 88, 90 };

    @Test
    void validarOrdenacaoBolha() {
        OrdenacaoBolha<Integer> ordenacaoBolha = new OrdenacaoBolha<>();
        ordenacaoBolha.setInfo(vetorNaoOrdenado.clone());
        ordenacaoBolha.ordenar();

        assertArrayEquals(vetorOrdenado, ordenacaoBolha.getInfo());
    }

    @Test
    void validarOrdenacaoBolhaOtimizado() {
        OrdenacaoBolhaOtimizada<Integer> ordenacaoBolhaOtimizada = new OrdenacaoBolhaOtimizada<>();
        ordenacaoBolhaOtimizada.setInfo(vetorNaoOrdenado.clone());
        ordenacaoBolhaOtimizada.ordenar();

        assertArrayEquals(vetorOrdenado, ordenacaoBolhaOtimizada.getInfo());
    }

    @Test
    void validarQuickSort() {
        QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.setInfo(vetorNaoOrdenado.clone());
        quickSort.ordenar();

        assertArrayEquals(vetorOrdenado, quickSort.getInfo());
    }

    @Test
    void validarMergeSort() {
        OrdenacaoMergeSort<Integer> mergeSort = new OrdenacaoMergeSort<>();
        mergeSort.setInfo(vetorNaoOrdenado.clone());
        mergeSort.ordenar();

        assertArrayEquals(vetorOrdenado, mergeSort.getInfo());
    }
}