package Exercicios.ex11.ArvoreBinariaBusca;

public class ArvoreBinariaBusca<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

    public void inserir(T info) {
        setRaiz(inserir(getRaiz(), info));
    }

    private NoArvoreBinaria<T> inserir(NoArvoreBinaria<T> no, T dado) {
        if (no == null) {
            return new NoArvoreBinaria<>(dado);
        }

        if (dado.compareTo(no.getInfo()) < 0) {
            no.setEsquerda(inserir(no.getEsquerda(), dado));
        } else if (dado.compareTo(no.getInfo()) > 0) {
            no.setDireita(inserir(no.getDireita(), dado));
        }

        return no;
    }

    @Override
    public NoArvoreBinaria<T> buscar(T info) {
        return buscar(getRaiz(), info);
    }

    private NoArvoreBinaria<T> buscar(NoArvoreBinaria<T> no, T dado) {
        if (no == null || no.getInfo().equals(dado)) {
            return no;
        }

        if (dado.compareTo(no.getInfo()) < 0) {
            return buscar(no.getEsquerda(), dado);
        } else {
            return buscar(no.getDireita(), dado);
        }
    }

    private NoArvoreBinaria<T> extrairSucessor(NoArvoreBinaria<T> excluir) {
        while (excluir.getEsquerda() != null) {
            excluir = excluir.getEsquerda();
        }
        return excluir;
    }

    public void retirar(T info) {
        setRaiz(retirar(getRaiz(), info));
    }

    private NoArvoreBinaria<T> retirar(NoArvoreBinaria<T> no, T dado) {
        if (no == null) {
            return null;
        }

        if (dado.compareTo(no.getInfo()) < 0) {
            no.setEsquerda(retirar(no.getEsquerda(), dado));
        } else if (dado.compareTo(no.getInfo()) > 0) {
            no.setDireita(retirar(no.getDireita(), dado));
        } else {
            // Caso 1: Nó folha (sem filhos)
            if (no.getEsquerda() == null && no.getDireita() == null) {
                return null;
            }

            // Caso 2: Nó com apenas um filho
            if (no.getEsquerda() == null) {
                return no.getDireita();
            }
            if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            // Caso 3: Nó com dois filhos
            // Encontra o sucessor inorder (menor valor na subárvore direita)
            NoArvoreBinaria<T> sucessor = encontrarMinimo(no.getDireita());

            // Substitui o dado do nó atual pelo dado do sucessor
            no.setInfo(sucessor.getInfo());

            // Remove o sucessor da subárvore direita
            no.setDireita(retirar(no.getDireita(), sucessor.getInfo()));
        }

        return no;
    }

    private NoArvoreBinaria<T> encontrarMinimo(NoArvoreBinaria<T> no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }
}
