package Trabalho.src.model.Ordenacao;

public class BubbleSort<T extends Comparable<T>> extends SortAbstract<T> {

    @Override
    public void ordenar() {
        T[] vetor = getInfo();

        for (int i = vetor.length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {

                if (vetor[j].compareTo(vetor[j + 1]) > 0) {
                    trocar(j, j + 1);
                }

            }
        }
    }
}
