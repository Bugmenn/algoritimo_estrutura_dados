package Exercicios.Prova2;

public class ArvoreBinaria<T> {

    private NoArvoreBinaria<T> raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void setRaiz(NoArvoreBinaria<T> raiz) {
        this.raiz = raiz;
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

    public boolean isDegenerada() {
        if (raiz == null || (raiz.getEsquerda() == null && raiz.getDireita() == null)) {
            return false;
        }

        NoArvoreBinaria<T> atual = raiz;

        while (atual != null) {
            NoArvoreBinaria<T> esq = atual.getEsquerda();
            NoArvoreBinaria<T> dir = atual.getDireita();

            if (esq != null && dir != null) {
                return false;
            }

            atual = (esq != null) ? esq : dir;
        }

        return true;
    }

    public static void main(String[] args) {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();

        NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
        NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3, no4, null);
        NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2, no3, null);
        NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1, no2, null);

        arvore.setRaiz(no1);

        boolean isDegenerada = arvore.isDegenerada();

        no4 = new NoArvoreBinaria<>(4);
        no3 = new NoArvoreBinaria<>(3, no4, null);
        no2 = new NoArvoreBinaria<>(2, null, no3);
        no1 = new NoArvoreBinaria<>(1, no2, null);

        arvore.setRaiz(no1);

        isDegenerada = arvore.isDegenerada();
    }
}
