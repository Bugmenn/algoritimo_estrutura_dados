package Exercicios.ArvoreBinariaBusca;

public abstract class ArvoreBinariaAbstract<T> {

    private NoArvoreBinaria<T> raiz;

    public ArvoreBinariaAbstract() {
    }

    protected void setRaiz(NoArvoreBinaria<T> raiz) {
        this.raiz = raiz;
    }

    public NoArvoreBinaria<T> getRaiz() {
        return raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public boolean pertence(T info) {
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvoreBinaria<T> no, T info) {
        if (no == null) {
            return false;
        }

        return no.getInfo().equals(info) || pertence(no.getEsquerda(), info) || pertence(no.getDireita(), info);
    }

    public abstract NoArvoreBinaria<T> buscar(T info);

    @Override
    public String toString() {
        return arvorePre(raiz);
    }

    private String arvorePre(NoArvoreBinaria<T> no) {
        if (no == null) {
            return "<>";
        }

        return "<" + no.getInfo() +
                arvorePre(no.getEsquerda()) +
                arvorePre(no.getDireita()) + ">";
    }

    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvoreBinaria<T> no) {
        if (no == null) {
            return 0;
        }

        return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
    }
}
