package Trabalho.src.model.Interfaces;

public interface IPilha<T> {
    /// Insere o valor
    /// @param info valor a inserir
    void push(T info);
    /// Retira o valor no topo da pilha
    T pop();
    /// Pega o valor no topo da pilha
    T peek();
    /// Verifica se a pilha está vazia
    /// @return {@code true} se estiver vazia, {@code false} caso não
    boolean estaVazia();
    /// Limpa a pilha
    void liberar();
}