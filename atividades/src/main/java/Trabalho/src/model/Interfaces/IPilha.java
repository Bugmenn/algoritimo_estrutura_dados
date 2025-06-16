package Trabalho.src.model.Interfaces;

public interface IPilha<T> {
    void push(T info);
    T pop();
    T peek();
    boolean estaVazia();
    void liberar();
}