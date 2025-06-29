package Exercicios.ex12.Ordenacao;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        quickSort(0, getInfo().length - 1);
    }

    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(inicio, fim);
            quickSort(inicio, indicePivo - 1);
            quickSort(indicePivo + 1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        T pivo = getInfo()[inicio];
        int i = inicio + 1;
        int j = fim;

        while (i <= j) {
            if (getInfo()[i].compareTo(pivo) <= 0) {
                i++;
            } else if (getInfo()[j].compareTo(pivo) > 0) {
                j--;
            } else {
                trocar(i, j);
                i++;
                j--;
            }
        }
        trocar(inicio, j);
        return j;
    }
}
