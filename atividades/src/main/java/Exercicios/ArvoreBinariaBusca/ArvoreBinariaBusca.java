package Exercicios.ArvoreBinariaBusca;

import Exercicios.Busca.ListaAbstract;

public class ArvoreBinariaBusca<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

    public void inserir(T info) {
        NoArvoreBinaria<T> novo = new NoArvoreBinaria<>(info);

        if (getRaiz() == null) {
            setRaiz(novo);
            return;
        }

        NoArvoreBinaria<T> no = getRaiz();
        NoArvoreBinaria<T> pai = null;

        while (no != null) {
            pai = no;
            if (info.compareTo(no.getInfo()) < 0) {
                no = no.getEsquerda();
            } else {
                no = no.getDireita();
            }
        }

        if (info.compareTo(pai.getInfo()) < 0) {
            pai.setEsquerda(novo);
        } else {
            pai.setDireita(novo);
        }
    }

    @Override
    public NoArvoreBinaria<T> buscar(T info) {
        NoArvoreBinaria<T> no = getRaiz();

        while (no != null) {
            int cmp = info.compareTo(no.getInfo());
            if (cmp == 0) {
                return no;
            } else if (cmp < 0) {
                no = no.getEsquerda();
            } else {
                no = no.getDireita();
            }
        }

        return null;
    }

    private NoArvoreBinaria<T> extrairSucessor(NoArvoreBinaria<T> excluir) {
        while (excluir.getEsquerda() != null) {
            excluir = excluir.getEsquerda();
        }
        return excluir;
    }

    public void retirar(T info) {
        NoArvoreBinaria<T> atual = getRaiz();
        NoArvoreBinaria<T> pai = null;

        // Localizar o nó a ser removido
        while (atual != null && !atual.getInfo().equals(info)) {
            pai = atual;
            if (info.compareTo(atual.getInfo()) < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        if (atual == null)
            return; // Não encontrado

        // Caso 1: Nó sem filhos
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            if (atual == getRaiz()) {
                setRaiz(null);
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
        }

        // Caso 2: Um filho (somente direita)
        else if (atual.getEsquerda() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getDireita());
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(atual.getDireita());
            } else {
                pai.setDireita(atual.getDireita());
            }
        }

        // Caso 2: Um filho (somente esquerda)
        else if (atual.getDireita() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getEsquerda());
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(atual.getEsquerda());
            } else {
                pai.setDireita(atual.getEsquerda());
            }
        }

        // Caso 3: Dois filhos
        else {
            // Encontrar o sucessor (menor da subárvore direita)
            NoArvoreBinaria<T> sucessor = atual.getDireita();
            NoArvoreBinaria<T> paiSucessor = atual;

            while (sucessor.getEsquerda() != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.getEsquerda();
            }

            // Substituir info do nó atual pelo do sucessor
            atual.setInfo(sucessor.getInfo());

            // Conectar o filho do sucessor ao pai do sucessor
            if (paiSucessor.getEsquerda() == sucessor) {
                paiSucessor.setEsquerda(sucessor.getDireita());
            } else {
                paiSucessor.setDireita(sucessor.getDireita());
            }
        }
    }
}
