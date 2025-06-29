package Exercicios.ex12.Ordenacao;

public class OrdenacaoMergeSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        mergeSort(0, getInfo().length - 1);
    }

    private void mergeSort(int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(inicio, meio);
            mergeSort(meio + 1, fim);
            merge(inicio, fim, meio);
        }
    }

    private void merge(int inicio, int fim, int meio) {
        int tamanhoEsquerda = meio - inicio + 1;
        int tamanhoDireita = fim - meio;

        @SuppressWarnings("unchecked")
        T[] esquerda = (T[]) new Comparable[tamanhoEsquerda];
        @SuppressWarnings("unchecked")
        T[] direita = (T[]) new Comparable[tamanhoDireita];

        // Copia dados para o array esquerda usando for
        for (int i = 0; i < tamanhoEsquerda; i++) {
            esquerda[i] = getInfo()[inicio + i];
        }

        // Copia dados para o array direita usando for
        for (int j = 0; j < tamanhoDireita; j++) {
            direita[j] = getInfo()[meio + 1 + j];
        }

        int i = 0, j = 0, k = inicio;
        while (i < tamanhoEsquerda && j < tamanhoDireita) {
            if (esquerda[i].compareTo(direita[j]) <= 0) {
                getInfo()[k] = esquerda[i];
                i++;
            } else {
                getInfo()[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < tamanhoEsquerda) {
            getInfo()[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < tamanhoDireita) {
            getInfo()[k] = direita[j];
            j++;
            k++;
        }
    }
}
