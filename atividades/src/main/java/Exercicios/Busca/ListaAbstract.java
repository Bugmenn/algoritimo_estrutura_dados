package Exercicios.Busca;

public abstract class ListaAbstract<T> {
    protected T[] info;
    protected int tamanho;

    private static int TAMANHO_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public ListaAbstract()
    {
        info = (T[]) new Object[TAMANHO_INICIAL];
        tamanho = 0;
    }

    public T[] getInfo() {
        return info;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /// pega o tamanho do vetor
    /// @return tamanho do vetor
    public int getTamanho() {
        return tamanho;
    }

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    /// Aumenta o tamanho do vetor em 10
    /// caso chegue no tamanho maximo
    protected void redimensionar()
    {
        T[] novoVetor = (T[]) new Object[info.length * 2];

        // cria uma copia do vetor
        // System.arraycopy(info, 0, novoVetor, 0, info.length);

        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = info[i];
        }

        info = novoVetor;
    }

    /// Insere o valor na última posição
    /// caso não tenha mais tamanho
    /// é aumentado
    public abstract void inserir(T valor);

    /// imprime os valores do vetor
    public void exibir()
    {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(info[i]);
        }
    }

    /// busca algum valor no vetor
    /// @return indice do valor ou -1 caso não existe
    public abstract int buscar(T valor);

    /// retira um valor do vetor e rearanja o vetor
    public void retirar(T valor)
    {
        int indice = buscar(valor);

        if (indice != -1) {
            for (int i = indice; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }

            info[tamanho - 1] = null;
            tamanho--;
        }
    }

    @SuppressWarnings("unchecked")
    /// volta o vetor ao estado original
    public void liberar()
    {
        info = (T[]) new Object[TAMANHO_INICIAL];
        tamanho = 0;
    }


    /// pega o valor da posição especificada
    /// @throws IndexOutOfBoundsException caso a posição for invalida
    public T obterElemento(int posicao)
    {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        return info[posicao];
    }

    /// verifica se o vetor está vazio
    /// @return boleano true se está vazio
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public String toString()
    {
        if (tamanho == 0)
            return "";

        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            if (i > 0)
                texto.append(",");

            texto.append(info[i]);
        }

        return texto.toString();
    }

    /// inverte a lista, faz o for usando metade do tamanho,
    /// assim guarda o primeiro elemento, pega o ultimo com base no (i - 1),
    /// por que index começa no 0 e tamanho começa no 1. Quando tamanho é subtraido
    /// por i, resulta em sempre pegar o valor oposto da lista.
    public void inverter()
    {
        for (int i = 0; i < tamanho / 2; i++) {
            T temp = info[i];
            info[i] = info[tamanho - 1 - i];
            info[tamanho - 1 - i] = temp;
        }
    }
}
