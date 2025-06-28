package Exercicios.ex8.ArvoreNAria;

public class NoArvore<T> {

    private T info;
    /// Primeiro filho
    private NoArvore<T> primeiro;
    /// Próximo irmão
    private NoArvore<T> proximo;

    public NoArvore(T info) {
        this.info = info;
        this.primeiro = null;
        this.proximo = null;
    }

    /// Dado um nó já criado, definir uma sub-árvore para
    /// este nó
    public void inserirFilho(NoArvore<T> sa) {
        if (this.primeiro == null) {
            this.primeiro = sa;
        } else {
            NoArvore<T> atual = this.primeiro;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = sa;
        }
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoArvore<T> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoArvore<T> primeiro) {
        this.primeiro = primeiro;
    }

    public NoArvore<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoArvore<T> proximo) {
        this.proximo = proximo;
    }
}
