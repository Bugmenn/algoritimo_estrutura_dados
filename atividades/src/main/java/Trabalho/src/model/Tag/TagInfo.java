package Trabalho.src.model.Tag;

public class TagInfo implements Comparable<TagInfo> {

    /// Nome da tag
    private final String nome;
    /// Quantidade da tag
    private int quantidade;

    public TagInfo(String nome) {
        this.nome = nome;
        this.quantidade = 1;
    }

    /// Compara o nome da tag com outro nome do objeto {@link TagInfo}
    /// @param other outro objeto {@link TagInfo}
    @Override
    public int compareTo(TagInfo other) {
        return this.nome.compareTo(other.nome);
    }

    /// Aumenta a quantidade que a tag especifica aparece
    public void incrementar() {
        quantidade++;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}