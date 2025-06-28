package Trabalho.src.model.Ordenacao;

public abstract class SortAbstract<T extends Comparable<T>> {

    /// Valor
    private T[] info;

    public void setInfo(T[] info) {
        this.info = info;
    }

    public T[] getInfo() {
        return info;
    }

    /// Troca os valores de lugar no vetor
    public void trocar(int valor1, int valor2) {
        T temp = info[valor1];
        info[valor1] = info[valor2];
        info[valor2] = temp;
    }

    /// Realiza a ordenação do vetor
    public abstract void ordenar();
}