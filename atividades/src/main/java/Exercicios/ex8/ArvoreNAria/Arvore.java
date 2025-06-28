package Exercicios.ex8.ArvoreNAria;

public class Arvore<T> {

    private NoArvore<T> raiz;

    public Arvore() {
        raiz = null;
    }

    public void setRaiz(NoArvore<T> raiz) {
        this.raiz = raiz;
    }

    public NoArvore<T> getRaiz() {
        return raiz;
    }

    public boolean pertence(T info) {
        if (raiz == null)
            return false;

        return pertence(raiz, info);
    }

    private boolean pertence(NoArvore<T> no, T info) {
        if (no == null) {
            return false;
        }
        if (no.getInfo().equals(info)) {
            return true;
        }

        NoArvore<T> filho = no.getPrimeiro();
        while (filho != null) {
            if (pertence(filho, info)) {
                return true;
            }
            filho = filho.getProximo();
        }

        return false;
    }

    @Override
    public String toString() {
        if (raiz == null)
            return "";

        return obterRepresentacaoTextual(raiz);
    }

    private String obterRepresentacaoTextual(NoArvore<T> no) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(no.getInfo());

        NoArvore<T> filho = no.getPrimeiro();
        while (filho != null) {
            sb.append(obterRepresentacaoTextual(filho));
            filho = filho.getProximo();
        }

        sb.append(">");
        return sb.toString();
    }

    public int contaNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvore<T> no) {
        if (no == null)
            return 0;

        return 1 + contarNos(no.getPrimeiro()) + contarNos(no.getProximo());
    }
}
