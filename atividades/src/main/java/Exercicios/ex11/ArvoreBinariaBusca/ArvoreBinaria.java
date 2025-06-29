package Exercicios.ex11.ArvoreBinariaBusca;

public class ArvoreBinaria<T> extends ArvoreBinariaAbstract<T> {

    public void setRaiz(NoArvoreBinaria<T> raiz) {
        super.setRaiz(raiz);
    }

    @Override
    public NoArvoreBinaria<T> buscar(T info) {
        return buscar(getRaiz(), info);
    }

    private NoArvoreBinaria<T> buscar(NoArvoreBinaria<T> no, T info) {
        if (no == null || no.getInfo().equals(info))
            return no;

        NoArvoreBinaria<T> resultadoEsquerda = buscar(no.getEsquerda(), info);
        if (resultadoEsquerda != null) {
            return resultadoEsquerda;
        }

        return buscar(no.getDireita(), info);
    }
}
