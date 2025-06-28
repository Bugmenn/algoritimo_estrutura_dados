package Exercicios.ex10.Busca;

public class ListaEstatica<T> extends ListaAbstract<T> {

    @Override
    public void inserir(T valor)
    {
        if (tamanho == info.length) {
            redimensionar();
        }

        Object[] infoLocal = getInfo();
        infoLocal[tamanho] = valor;
        setTamanho(tamanho + 1);
    }

    @Override
    public int buscar(T valor)
    {
        Object[] info = getInfo();

        for (int i = 0; i < tamanho; i++) {
            if (info[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }
}
