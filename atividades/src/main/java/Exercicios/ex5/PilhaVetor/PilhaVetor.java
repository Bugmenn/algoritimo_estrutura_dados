package Exercicios.ex5.PilhaVetor;

public class PilhaVetor<T> implements Pilha<T> {

    private T[] info;
    private int limite;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public PilhaVetor(int limite) {
        info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
    }

    /// Insere um valor no topo da pilha
    /// @throws PilhaCheiaException caso o tamanho tenha chegado ao limite
    @Override
    public void push(T info) {
        if (limite == tamanho) {
            throw new PilhaCheiaException("Capacidade esgotada da pilha");
        }

        this.info[tamanho] = info;
        tamanho++;
    }

    /// Tira o valor no topo da pilha
    ///
    @Override
    public T pop() {
        T valor = peek();
        tamanho--;
        info[tamanho] = null;
        return valor;
    }

    /// Pega o valor no topo da lista
    /// @throws PilhaVaziaException caso a pilha estiver vazia
    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException("Pilha está vazia");
        }

        return info[tamanho-1];
    }

    /// Verifica se a pilha está vazia
    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /// Tira todos os elementos da pilha
    @Override
    public void liberar() {
        while (!estaVazia()) {
            pop();
        }
    }

    @Override
    public String toString() {
        if (tamanho == 0)
            return "";

        StringBuilder texto = new StringBuilder();
        for (int i = tamanho-1; i >= 0; i--) {
            if (i < tamanho-1)
                texto.append(",");

            texto.append(info[i]);
        }

        return texto.toString();
    }

    /// Adiciona outros elementos de outra pilha na pilha atual
    /// @throws PilhaCheiaException caso a pilha atual não consiga suportar os elementos
    public void concatenar(PilhaVetor<T> p) {
        if (this.tamanho + p.tamanho > this.limite) {
            throw new PilhaCheiaException("Não há espaço suficiente na pilha para concatenar");
        }

        PilhaVetor<T> copia = new PilhaVetor<>(p.tamanho);

        for (int i = p.tamanho - 1; i >= 0; i--) {
            copia.push(p.info[i]);
        }

        while (!copia.estaVazia()) {
            this.push(copia.pop());
        }
    }
}
