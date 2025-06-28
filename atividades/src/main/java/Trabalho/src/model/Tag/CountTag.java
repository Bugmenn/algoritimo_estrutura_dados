package Trabalho.src.model.Tag;

import Trabalho.src.model.Lista.ListaEncadeada;
import Trabalho.src.model.Lista.NoLista;
import Trabalho.src.model.Ordenacao.*;

public class CountTag {

    private final ListaEncadeada<TagInfo> tags = new ListaEncadeada<>();

    /// Adiciona a tag na lista
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

    public TagInfo[] getTagsOrdenadas() {
        
        int tamanho = tags.obterComprimento();
        TagInfo[] array = new TagInfo[tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            array[i] = tags.obterNo(i).getInfo();
        }

        SortAbstract<TagInfo> ordenador;

        if (array.length <= 10) {
            ordenador = new BubbleSort<TagInfo>();
        } else {
            ordenador = new QuickSort<TagInfo>();
        }

        ordenador.setInfo(array); 
        ordenador.ordenar();
        return array;
    }
}