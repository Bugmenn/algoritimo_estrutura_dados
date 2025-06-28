package Exercicios.Prova2.Exercicio2;

import Exercicios.ex7.ArvoreBinaria.NoArvoreBinaria;

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

    public int contarFolhas(NoArvoreBinaria<T> sa) {
        if (sa == null) {
            return 0;
        } else if (sa.getEsquerda() == null && sa.getDireita() == null) {
            return 1;
        }

        return contarFolhas(sa.getEsquerda()) + contarFolhas(sa.getDireita());
    }

    public static void main(String[] args) {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        NoArvoreBinaria<Integer> raiz = new NoArvoreBinaria<>(1);

        NoArvoreBinaria<Integer> esquerdaRaiz = new NoArvoreBinaria<>(2,
                null, new NoArvoreBinaria<Integer>(4));

        NoArvoreBinaria<Integer> direitaRaiz = new NoArvoreBinaria<>(3,
                new NoArvoreBinaria<Integer>(5), new NoArvoreBinaria<Integer>(6));

        raiz.setEsquerda(esquerdaRaiz);
        raiz.setDireita(direitaRaiz);
        arvore.setRaiz(raiz);

        int quantidadeFolhas = arvore.contarFolhas(arvore.raiz);
        System.out.println("quantidade folhas:" + quantidadeFolhas);
    }
}
