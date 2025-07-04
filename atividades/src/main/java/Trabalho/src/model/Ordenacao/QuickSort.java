package Trabalho.src.model.Ordenacao;

public class QuickSort<T extends Comparable<T>> extends SortAbstract<T> {

    /// Ordena o vetor com base no quick sort
    @Override
    public void ordenar() {
        quickSort(0, getInfo().length - 1);
    }

    /// Metodo que divide o vetor em subvetores
    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            int pivo = particionar(inicio, fim); // Particiona o vetor e pega a posição final do pivo
            quickSort(inicio, pivo - 1); // Ordena subparte da esquerda do pivo
            quickSort(pivo + 1, fim); // Ordena subparte da direita do pivo
        }
    }

    /// Reorganiza os elementos em torno do pivo
    private int particionar(int inicio, int fim) {
        T[] vetor = getInfo();
        T pivo = vetor[inicio];
        int esquerda = inicio + 1;
        int direita = fim;

        while (true) {
            while (esquerda <= fim && vetor[esquerda].compareTo(pivo) <= 0) {
                esquerda++;
            }
            while (direita >= inicio && vetor[direita].compareTo(pivo) > 0) {
                direita--;
            }
            if (esquerda >= direita) {
                break;
            }
            trocar(esquerda, direita);
        }

        trocar(inicio, direita);
        return direita;
    }
}
