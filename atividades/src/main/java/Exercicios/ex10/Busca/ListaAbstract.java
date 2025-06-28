package Exercicios.ex10.Busca;

public abstract class ListaAbstract<T> {

    protected Object[] info;
    protected int tamanho;

    private static final int TAMANHO_INICIAL = 10;

    public ListaAbstract()
    {
        info = new Object[TAMANHO_INICIAL];
        tamanho = 0;
    }

    /// Pega o tamanho do vetor
    /// @return tamanho do vetor
    public int getTamanho() {
        return tamanho;
    }

    protected void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    protected Object[] getInfo() {
        return info;
    }

    @SuppressWarnings("ManualArrayCopy")
    /// Aumenta o tamanho do vetor em 10
    /// caso chegue no tamanho maximo
    protected void redimensionar()
    {
        Object[] novoVetor = new Object[info.length * 2];

        for (int i = 0; i < tamanho; i++) {
            novoVetor[i] = info[i];
        }

        info = novoVetor;
    }

    /// Insere o valor na última posição
    /// caso não tenha mais tamanho
    /// é aumentado
    public abstract void inserir(T valor);

    /// Imprime os valores do vetor
    public void exibir()
    {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(info[i]);
        }
    }

    /// Busca algum valor no vetor
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

    /// volta o vetor ao estado original
    public void liberar()
    {
        info = new Object[TAMANHO_INICIAL];
        tamanho = 0;
    }


    /// pega o valor da posição especificada
    /// @throws IndexOutOfBoundsException caso a posição for invalida
    public T obterElemento(int pos)
    {
        if (pos < 0 || pos >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + pos);
        }
        return (T) info[pos];
    }

    /// verifica se o vetor está vazio
    /// @return booleano true se está vazio
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
}
