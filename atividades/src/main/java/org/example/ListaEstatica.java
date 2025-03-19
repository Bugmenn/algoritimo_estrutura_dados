package org.example;

public class ListaEstatica {
    private int[] info;
    private int tamanho;

    public ListaEstatica()
    {
        info = new int[10];
        tamanho = 0;
    }

    /// aumenta o tamanho do vetor em 10
    /// caso chegue no tamanho maximo
    private void redimensionar()
    {
        int[] novoVetor = new int[info.length + 10];

        // cria uma copia do vetor
        // System.arraycopy(info, 0, novoVetor, 0, info.length);

        for (int i = 0; i < tamanho - 1; i++) {
            novoVetor[i] = info[i];
        }

        info = novoVetor;
    }

    /// insere o valor na ultima posição
    /// caso não tenha mais tamanho
    /// é aumentado
    public void inserir(int valor)
    {
        if (tamanho >= info.length)
        {
            redimensionar();
        }

        info[tamanho] = valor;
        tamanho += 1;
    }

    /// imprime os valores do vetor
    public void exibir()
    {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(info[i]);
        }
    }

    /// busca algum valor no vetor
    /// @return indice do valor ou -1 caso não existe
    public int buscar(int valor)
    {
        for (int i = 0; i < tamanho; i++) {
            if (info[i] == valor)
            {
                return i;
            }
        }

        return -1;
    }

    /// retira um valor do vetor e rearanja o vetor
    public void retirar(int valor)
    {
        int indice = buscar(valor);
        if (indice != -1) {
            for (int i = indice; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            info[tamanho - 1] = 0;
            tamanho--;
        }
    }

    /// volta o vetor ao estado original
    public void liberar()
    {
        info = new int[10];
        tamanho = 0;
    }


    /// pega o valor da posição especificada
    /// @throws IndexOutOfBoundsException caso a posição for invalida
    public int obterElemento(int posicao)
    {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        return info[posicao];
    }

    /// verifica se o vetor está vazio
    /// @return boleano
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /// pega o tamanho do vetor
    /// @return tamanho do vetor
    public int getTamanho() {
        return tamanho;
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
