package Exercicios.ArvoreNAria;

public class NoArvore<T> {

    private T info;
    /// Primeiro filho
    private NoArvore<T> primeiro;
    /// Próximo irmão
    private NoArvore<T> proximo;

    public NoArvore(T info) {
        this.info = info;
    }

    /// Dado um nó já criado, definir uma sub-árvore para
    /// este nó
    public void inserirFilho(NoArvore<T> sa) {
        sa.proximo = primeiro;
        primeiro = sa;
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
