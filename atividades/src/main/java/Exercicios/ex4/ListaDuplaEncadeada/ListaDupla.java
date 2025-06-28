package Exercicios.ex4.ListaDuplaEncadeada;

public class ListaDupla<T> {

    private NoListaDupla<T> primeiro;

    public ListaDupla() {
        primeiro = null;
    }

    public NoListaDupla<T> getPrimeiro() {
        return primeiro;
    }

    /// Adiciona um valor no primeiro e coloca o valor
    /// no nó, caso seja o primeiro, o valor primeiro é null
    /// e caso o primeiro não seja nulo, anterior receba o valor
    public void inserir(T valor) {
        NoListaDupla<T> novo = new NoListaDupla<>();
        novo.setInfo(valor);
        novo.setProximo(primeiro);
        novo.setAnterior(null);

        if (primeiro != null)
            primeiro.setAnterior(novo);

        primeiro = novo;
    }

    /// Busca o valor informado dentro dos nós
    /// @return O nó com o valor ou null
    public NoListaDupla<T> buscar(T valor) {
        NoListaDupla<T> p = primeiro;

        while (p != null) {
            if (p.getInfo().equals(valor))
                return p;

            p = p.getProximo();
        }

        return null;
    }

    public void retirar(T valor) {
        NoListaDupla<T> p = buscar(valor);

        if (p != null) {
            if (primeiro == p) { // primeiro elemento?
                primeiro = p.getProximo();
            }
            else {
                p.getAnterior().setProximo(p.getProximo());
            }

            if (p.getProximo() != null) { // não é o ultimo
                p.getProximo().setAnterior(p.getAnterior());
            }
        }
    }

    public void exibirOrdemInversa() {
        if (primeiro == null) {
            System.out.println("Lista vazia");
            return;
        }

        NoListaDupla<T> p = primeiro;

        // acha o ultimo nó da lista
        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        // percorre a lista ao contrario
        while (p != null) {
            System.out.print(p.getInfo() + " ");
            p = p.getAnterior();
        }

        System.out.println();
    }

    public void liberar() {
        NoListaDupla<T> p = primeiro;

        // pega o proximo antes de limpa-lo
        while (p != null) {
            NoListaDupla<T> proximo = p.getProximo();
            p.setProximo(null);
            p.setAnterior(null);
            p = proximo;
        }

        primeiro = null;
    }

    @Override
    public String toString() {
        NoListaDupla<T> p = primeiro;
        StringBuilder texto = new StringBuilder();

        while (p != null) {
            texto.append(p.getInfo());

            if (p.getProximo() != null) {
                texto.append(", ");
            }

            p = p.getProximo();
        }

        return texto.toString();
    }
}
