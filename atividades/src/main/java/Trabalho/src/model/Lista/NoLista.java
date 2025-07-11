package Trabalho.src.model.Lista;

public class NoLista<T> {

    /// Valor
    private T info;
    /// Proximo valor
    private NoLista<T> proximo;

    public T getInfo(){
        return info;
    }

    public void setInfo(T info){
        this.info = info;
    }

    public NoLista<T> getProximo(){
        return proximo;
    }

    public void setProximo(NoLista<T> proximo){
        this.proximo = proximo;
    }
}
