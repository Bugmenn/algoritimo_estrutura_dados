package Trabalho.src.model.Ordenacao;

public abstract class SortAbstract<T extends Comparable<T>> {

    private T[] info;

    public void setInfo(T[] info) {
        this.info = info;
    }

    public T[] getInfo() {
        return info;
    }

    public void trocar(int valor1, int valor2) {
        T temp = info[valor1];
        info[valor1] = info[valor2];
        info[valor2] = temp;
    }

    public abstract void ordenar();
}