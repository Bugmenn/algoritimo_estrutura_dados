package Trabalho.src.validator;

import java.io.BufferedReader;
import java.io.FileReader;
import Trabalho.src.model.Pilha.PilhaLista;
import Trabalho.src.model.Tag.CountTag;
import Trabalho.src.utils.Util;

public class Validator {

    /// Vetor de tags que não precisam de fechamento
    private final String[] singletons = {
            "meta", "base", "br", "col", "command", "embed", "hr", "img", "input",
            "link", "param", "source", "!doctype"
    };
    /// Contador de tags
    private final CountTag contadorTag = new CountTag();
    /// Relatório de validação
    private final StringBuilder report = new StringBuilder();

    public String getReport() {
        return report.toString();
    }

    public CountTag getCountTag() {
        return contadorTag;
    }

    /// Valida as tags do arquivo
    /// @param caminho URL do arquivo
    /// @return {@code true} se o arquivo está bem formatado, {@code false} caso contrário.
    public boolean validar(String caminho) {

        if (!isArquivoHTML(caminho)) {
            report.append("Erro: o arquivo informado não é um arquivo HTML válido (.html ou .htm)\n");
            return false;
        }

        PilhaLista<String> pilhaLista = new PilhaLista<>();
        int numeroLinha = 0;
        boolean isComent = false, isScript = false, isStyle = false, haveError = false;

        // Inicia o leitor de arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;

            // Le as linhas até acabar
            while ((linha = reader.readLine()) != null) {
                numeroLinha++;
                linha = linha.trim();

                if (linha.isEmpty())
                    continue;

                ResultadoProcessamento resultado = processarLinha(linha, numeroLinha, pilhaLista, isComent, isScript, isStyle);
                isComent = resultado.isComent;
                isScript = resultado.isScript;
                isStyle = resultado.isStyle;
                haveError |= resultado.haveError;
            }

            // Verifica tags que ficaram abertas
            while (!pilhaLista.estaVazia()) {
                report.append("Faltando tag final para <").append(pilhaLista.pop()).append(">\n");
                haveError = true;
            }

        } catch (Exception e) {
            report.append("Erro ao ler arquivo: ").append(e.getMessage()).append("\n");
            return false;
        }

        if (haveError)
            return false;

        report.append("Arquivo está bem formatado!\n");
        return true;
    }

    /// Verifica se o arquivo é um html
    /// @param caminho URL do arquivo
    /// @return boolean {@code true} se é um html, {@code false} caso contrario
    private boolean isArquivoHTML(String caminho) {
        return caminho.toLowerCase().endsWith(".html") || caminho.toLowerCase().endsWith(".htm");
    }

    /// Realiza o processamento da linha
    /// @param linha linha sendo lida
    /// @param numeroLinha o número da linha no arquivo
    /// @param pilhaLista pilha de tags abertas
    /// @param isComent se é comentário
    /// @param isScript se é script
    /// @param isStyle se é style
    /// @return objeto {@link ResultadoProcessamento}
    private ResultadoProcessamento processarLinha(String linha, int numeroLinha, PilhaLista<String> pilhaLista, boolean isComent, boolean isScript, boolean isStyle) {

        boolean haveError = false;
        int position = 0;

        // Anda pela tags no arquivo
        while (position < linha.length()) {

            // Ignora comentários e verifica se o comentário acaba
            if (isComent) {
                int fimComentario = linha.indexOf("-->", position);
                if (fimComentario == -1)
                    return new ResultadoProcessamento(true, isScript, isStyle, haveError);
                position = fimComentario + 3;
                isComent = false;
                continue;
            }

            // Verifica se começa o comentário
            int inicioComentario = linha.indexOf("<!--", position);
            if (inicioComentario != -1 && inicioComentario == linha.indexOf("<", position)) {
                isComent = true;
                position = inicioComentario + 4;
                continue;
            }

            // Localiza a abertura da tag
            int inicio = linha.indexOf("<", position);
            if (inicio == -1)
                break;

            // Verifica o fechamento da tag
            int fim = encontrarTagFim(linha, inicio);
            if (fim == -1)
                break;

            // Pega a parte do nome da tag
            String tagCompleta = linha.substring(inicio + 1, fim).trim();
            boolean fechamentoTag = tagCompleta.startsWith("/");
            String tagName = Util.extractTagName(tagCompleta).toLowerCase();

            // Controle de script/style
            if (!isScript && !isStyle && !fechamentoTag) {
                if (tagName.equals("script")) isScript = true;
                else if (tagName.equals("style")) isStyle = true;
            } else if (isScript && fechamentoTag && tagName.equals("script")) {
                isScript = false;
                position = fim + 1;
                continue;
            } else if (isStyle && fechamentoTag && tagName.equals("style")) {
                isStyle = false;
                position = fim + 1;
                continue;
            }

            if (isScript || isStyle) {
                position = fim + 1;
                continue;
            }

            // Verifica se não precisa de tag de fechamento
            boolean isSingletonTag = verificarSingletonTag(tagCompleta, tagName);
            if (isSingletonTag) { // caso não precise adiciona direto na quantidade de tags que apareceram
                contadorTag.add(tagName);
            } else if (fechamentoTag) { // caso a tag feche
                if (pilhaLista.estaVazia()) { // se a tag fecha mas não abre aparece nos erros
                    report.append("Erro linha ").append(numeroLinha)
                            .append(": tag final </").append(tagName)
                            .append("> sem correspondente de abertura.\n");
                    haveError = true;
                } else {
                    String lastOpened = pilhaLista.pop(); // pega a última tag que foi aberta
                    if (!lastOpened.equals(tagName)) { // se a tag de fechamento é a mesma não é a mesma que abriu
                        report.append("Erro linha ").append(numeroLinha)
                                .append(": esperava </").append(lastOpened)
                                .append(">, mas encontrou </").append(tagName).append(">\n");
                        haveError = true;

                        // Verifica caso a tag de fechamento não é a que deveria fechar
                        while (!pilhaLista.estaVazia() && !pilhaLista.peek().equals(tagName)) {
                            String missing = pilhaLista.pop();
                            report.append("Faltando tag final para <").append(missing).append(">\n");
                        }
                        if (!pilhaLista.estaVazia()) {
                            pilhaLista.pop();
                        }
                    }
                }
            } else { // caso a tag abra coloca na pilha e adiciona na quantidade
                pilhaLista.push(tagName);
                contadorTag.add(tagName);
            }

            position = fim + 1;
        }

        return new ResultadoProcessamento(isComent, isScript, isStyle, haveError);
    }

    /// Classe interna para fazer o processamento do arquivo
    private static class ResultadoProcessamento {
        boolean isComent, isScript, isStyle, haveError;

        public ResultadoProcessamento(boolean isComent, boolean isScript, boolean isStyle, boolean haveError) {
            this.isComent = isComent;
            this.isScript = isScript;
            this.isStyle = isStyle;
            this.haveError = haveError;
        }
    }

    /// Encontra a posição do fechamento da tag
    /// @param linha linha lida atualmente
    /// @param inicio posição que começa a abertura da tag
    private int encontrarTagFim(String linha, int inicio) {
        int fim = inicio + 1;
        boolean isDentroDeAspas = false;
        char quoteChar = 0;

        while (fim < linha.length()) {
            char caractere = linha.charAt(fim);

            if ((caractere == '"' || caractere == '\'') && (fim == inicio + 1 || linha.charAt(fim - 1) != '\\')) {
                if (!isDentroDeAspas) {
                    isDentroDeAspas = true;
                    quoteChar = caractere;
                } else if (caractere == quoteChar) {
                    isDentroDeAspas = false;
                }
            } else if (caractere == '>' && !isDentroDeAspas) {
                return fim;
            }

            fim++;
        }

        return -1;
    }

    /// Verifica se a tag não precisa de fechamento
    private boolean verificarSingletonTag(String tagCompleta, String tagName) {
        if (tagCompleta.endsWith("/")) {
            return true;
        }

        for (String singleton : singletons) {
            if (singleton.equals(tagName)) {
                return true;
            }
        }

        return false;
    }
}