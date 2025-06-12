package Exercicios.Prova2;

public class NoArvoreBinaria<T> {

    private NoArvoreBinaria<T> esquerda, direita;
    private T info;

    public NoArvoreBinaria(T info) {
        this.info = info;
        esquerda = null;
        direita = null;
    }

    public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
        this.info = info;
        this.direita = dir;
        this.esquerda = esq;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public NoArvoreBinaria<T> getDireita() {
        return direita;
    }

    public void setDireita(NoArvoreBinaria<T> direita) {
        this.direita = direita;
    }

    public NoArvoreBinaria<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvoreBinaria<T> esquerda) {
        this.esquerda = esquerda;
    }
}
