package Trabalho.src.model.Tag;

import Trabalho.src.model.Lista.ListaEncadeada;
import Trabalho.src.model.Lista.NoLista;
import Trabalho.src.model.Ordenacao.*;

public class CountTag {

    /// Lista de tags
    private final ListaEncadeada<TagInfo> tags = new ListaEncadeada<>();

    /// Adiciona a tag na lista, caso a tag já exista na lista é aumentado a quantidade de ocorrências
    /// @param tag tag a ser inserida na lista
    public void add(String tag) {
        tag = tag.toLowerCase();

        int tamanho = tags.obterComprimento();
        for (int i = 0; i < tamanho; i++) {
            
            NoLista<TagInfo> noTagInfo = tags.obterNo(i);
            TagInfo tagInfo = noTagInfo.getInfo();

            if (tagInfo.getNome().equals(tag)) {
                tagInfo.incrementar();
                return;
            }
        }
        tags.inserir(new TagInfo(tag));
    }

    /// Ordena as tags da lista, cria um vetor e realiza a ordenação com base no vetor.
    /// Caso o vetor tenha 10 ou menos elementos realiza o bubble sort.
    /// Se tiver mais de 10 elementos realiza quick sort
    public TagInfo[] getTagsOrdenadas() {
        
        int tamanho = tags.obterComprimento();
        TagInfo[] array = new TagInfo[tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            array[i] = tags.obterNo(i).getInfo();
        }

        SortAbstract<TagInfo> ordenador = array.length <= 10 ? new BubbleSort<>() : new QuickSort<>();
        ordenador.setInfo(array);
        ordenador.ordenar();

        return array;
    }
}