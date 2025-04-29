package Exercicios.PilhaLista;

public class PilhaLista<T> implements Pilha<T> {

    private ListaEncadeada<T> lista;

    public PilhaLista() {
        lista = new ListaEncadeada<>();
    }

    /// Insere dado
    public void push(T info) {
        lista.inserir(info);
    }

    ///  Retira dado do topo
    public T pop() {
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    /// Pega dado do topo
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException("Pilha está vazia");
        }

        return lista.getPrimeiro().getInfo();
    }

    public boolean estaVazia() {
        return lista.estaVazia();
    }

    /// Limpa
    public void liberar() {
        while (lista.getPrimeiro() != null) {
            pop();
        }
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
