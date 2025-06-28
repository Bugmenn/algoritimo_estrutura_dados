package Exercicios.ex5.PilhaLista;

public class ListaEncadeada<T> {

    private NoLista<T> primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    /// Insere um novo nó, apontando o proximo usando primeiro como base
    public void inserir(T info) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(info);
        // transforma o novo valor no primeiro da lista
        // o antigo primeiro vira segundo
        novo.setProximo(primeiro);
        primeiro = novo;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    /// Busca um valor dentro dos nós
    /// @return o valor caso encontrado, caso não retorna null
    public NoLista<T> buscar(T info) {
        NoLista<T> p = primeiro;

        while (p != null) {
            if (p.getInfo().equals(info)) {
                return p;
            }
            p = p.getProximo();
        }

        return null;
    }

    /// Procura nó que contém dado a ser removido,
    /// guardando o anterior
    public void retirar(T info) {
        NoLista<T> anterior = null;
        NoLista<T> p = primeiro;

        while (p != null && !p.getInfo().equals(info)) {
            anterior = p;
            p = p.getProximo();
        }

        if (p != null) {
            if (p == primeiro) {
                // se o valor for o segundo
                // joga no primeiro
                primeiro = p.getProximo();
            } else {
                // se o valor
                anterior.setProximo(p.getProximo());
            }
        }
    }


    /// Pega o comprimento da lista
    /// @return int sendo o tamanho
    public int obterComprimento() {
        NoLista<T> p = primeiro;
        int comprimento = 0;

        while (p != null) {
            comprimento++;
            p = p.getProximo();
        }

        return comprimento;
    }

    /// Obtém o nó especifico
    /// @throws IndexOutOfBoundsException caso o index não seja valido
    public NoLista<T> obterNo(int idx) {
        if (idx < 0 || idx > obterComprimento())
            throw new IndexOutOfBoundsException("Tamanho incorreto");

        NoLista<T> p = primeiro;

        for (int i = 0; i <= obterComprimento()-1; i++) {
            if (i == idx)
                return p;

            p = p.getProximo();
        }

        return new NoLista<T>();
    }

    /// Transforma a lista em uma string com os valores separados por virgula
    /// @return lista transformada
    @Override
    public String toString() {
        NoLista<T> p = primeiro;
        StringBuilder texto = new StringBuilder();

        while (p != null) {
            texto.append(p.getInfo());

            // se for o ultimo valor, ele pula
            if (p.getProximo() != null) {
                texto.append(", ");
            }

            p = p.getProximo();
        }

        return texto.toString();
    }

}
