package Exercicios.Busca;

public class ListaOrdenada<T extends Comparable<T>> extends ListaAbstract<T> {

    public void inserir(T valor)
    {
        Object[] info = getInfo();
        int tamanho = getTamanho();

        if (tamanho >= info.length)
        {
            redimensionar();
            info = getInfo();
        }

        // Faz a verificação para ordenar os valores
        // Caso achar que o valor informado é menor que o número anterior
        // i mantém o valor para o próximo laço de repetição
        int i;
        for (i = 0; i < tamanho; i++) {
            if (valor.compareTo((T) info[i]) < 0) {
                break;
            }
        }

        for (int j = tamanho; j > i; j--) {
            info[j] = info[j - 1];
        }

        info[i] = valor;
        setTamanho(tamanho + 1);
    }

    public int buscar(T valor)
    {
        Object[] info = getInfo();
        int inicio = 0;
        int fim = getTamanho() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = valor.compareTo((T)info[meio]);

            if (comparacao == 0) {
                return meio;
            } else if (comparacao < 0) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }

        return -1;
    }
}
