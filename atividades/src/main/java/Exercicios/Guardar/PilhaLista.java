package Exercicios.Guardar;


import Exercicios.ex5.PilhaLista.ListaEncadeada;
import Exercicios.ex5.PilhaLista.Pilha;
import Exercicios.ex5.PilhaLista.PilhaVaziaException;

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

    public boolean validarBalanceamento(String expr) {
        PilhaLista<Character> pilha = new PilhaLista<>();

        for (int i = 0; i < expr.length(); i++) {
            char caractere = expr.charAt(i);

            if (caractere == '(' || caractere == '[' || caractere == '{') {
                pilha.push(caractere);
            }
            else if (caractere == ')' || caractere == ']' || caractere == '}') {
                // verifica caso a pilha não tenha nada
                if (pilha.estaVazia()) {
                    return false;
                }

                char topo = pilha.pop();

                // verifica caso o caracter não seja de acordo com o topo
                if ((caractere == ')' && topo != '(') || (caractere == ']' && topo != '[')
                        || (caractere == '}' && topo != '{')) {
                    return false;
                }
            }
        }

        // verifica no final para que não sobre nada
        return pilha.estaVazia();
    }

    public static void main(String[] args) {
        PilhaLista<String> teste = new PilhaLista<>();
        boolean verdade = teste.validarBalanceamento("a = (b + c) * [d / e]");
        boolean falso = teste.validarBalanceamento("a = (b + c * [d / e]");
    }
}
