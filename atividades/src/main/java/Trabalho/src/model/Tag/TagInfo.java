package Trabalho.src.model.Tag;

public class TagInfo implements Comparable<TagInfo> {

    /// Nome da tag
    private final String nome;
    /// Quantidade da tag
    private int contador;

    public TagInfo(String nome) {
        this.nome = nome;
        this.contador = 1;
    }

    @Override
    public int compareTo(TagInfo other) {
        return this.nome.compareTo(other.nome);
    }

    public void incrementar() {
        contador++;
    }

    public String getNome() {
        return nome;
    }

    public int getContador() {
        return contador;
    }
}