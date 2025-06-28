package Exercicios.Busca;

public class ListaOrdenada<T extends Comparable<T>> extends ListaAbstract<T> {

    @Override
    public void inserir(T valor)
    {
        if (tamanho == info.length) {
            redimensionar();
        }

        Object[] info = getInfo();

        // Verifica a posição correta para inserir e mantém a ordem
        int posicao = 0;
        while (posicao < tamanho && valor.compareTo((T) info[posicao]) > 0) {
            posicao++;
        }

        // Anda as informações para direita
        for (int i = tamanho; i > posicao; i--) {
            info[i] = info[i - 1];
        }

        info[posicao] = valor;
        setTamanho(tamanho + 1);
    }

    @Override
    public int buscar(T valor)
    {
        Object[] info = getInfo();

        int inicio = 0;
        int fim = tamanho - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = valor.compareTo((T) info[meio]);

            if (comparacao == 0) {
                return meio;
            } else if (comparacao < 0) { // Busca na metade da esquerda
                fim = meio - 1;
            } else { // Busca na metade da direita
                inicio = meio + 1;
            }
        }

        return -1;
    }
}
