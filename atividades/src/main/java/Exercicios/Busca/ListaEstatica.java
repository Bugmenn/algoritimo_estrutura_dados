package Exercicios.Busca;

public class ListaEstatica<T> extends ListaAbstract<T> {

    @Override
    public void inserir(T valor)
    {
        T[] info = getInfo();

        if (getTamanho() >= info.length)
        {
            redimensionar();
            info = getInfo();
        }

        info[getTamanho()] = valor;
        setTamanho(getTamanho() + 1);
    }

    @Override
    public int buscar(T valor)
    {
        T[] info = getInfo();

        for (int i = 0; i < getTamanho(); i++) {
            if (info[i].equals(valor))
            {
                return i;
            }
        }

        return -1;
    }
}
