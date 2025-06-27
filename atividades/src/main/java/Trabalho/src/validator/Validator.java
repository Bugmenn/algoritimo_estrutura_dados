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
    /// @return true se o arquivo está bem formatado, false caso contrário.
    public boolean validar(String caminho) {

        if (!caminho.toLowerCase().endsWith(".html") && !caminho.toLowerCase().endsWith(".htm")) {
            report.append("Erro: o arquivo informado não é um arquivo HTML válido (.html ou .htm)\n");
            return false;
        }

        PilhaLista<String> pilhaLista = new PilhaLista<>();
        int numeroLinha = 0;
        boolean isComent = false, isScript = false, isStyle = false, haveError = false;

        // Inicia o leitor de arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                numeroLinha++;
                linha = linha.trim();

                if (linha.isEmpty()) 
                    continue;

                // Anda pela tags no arquivo
                int position = 0;
                while (position < linha.length()) {

                    // Ignora comentários
                    if (isComent) {
                        int fimComentario = linha.indexOf("-->", position);

                        if (fimComentario == -1) 
                            break;
                            
                        position = fimComentario + 3;
                        isComent = false;
                        continue;
                    }

                    // Verifica o início de comentários
                    int inicioComentario = linha.indexOf("<!--", position);
                    if (inicioComentario != -1 && inicioComentario == linha.indexOf("<", position)) {
                        isComent = true;
                        position = inicioComentario + 4;
                        continue;
                    }

                    // Localiza o inicio da tag
                    int inicio = linha.indexOf("<", position);
                    if (inicio == -1) 
                        break;

                    if (isComent) {
                        position = inicio + 1;
                        continue;
                    }

                    int fim = encontrarTagFim(linha, inicio);
                    if (fim == -1) 
                        break;

                    String tagCompleta = linha.substring(inicio + 1, fim).trim();
                    boolean fechamentoTag = tagCompleta.startsWith("/");
                    String tagName = Util.extractTagName(tagCompleta).toLowerCase();

                    // Identifica entrada e saída de <script> e <style>
                    if (!isScript && !isStyle && !fechamentoTag) {
                        if (tagName.equals("script")) {
                            isScript = true;
                        } else if (tagName.equals("style")) {
                            isStyle = true;
                        }
                    } else if (isScript && fechamentoTag && tagName.equals("script")) {
                        isScript = false;
                        position = fim + 1;
                        continue;
                    } else if (isStyle && fechamentoTag && tagName.equals("style")) {
                        isStyle = false;
                        position = fim + 1;
                        continue;
                    }

                    // Ignora o conteúdo interno de <script> e <style>
                    if (isScript || isStyle) {
                        position = fim + 1;
                        continue;
                    }

                    boolean isSingletonTag = verificarSingletonTag(tagCompleta, tagName);

                    // Realiza as verificações das tags
                    if (isSingletonTag) { // se for singleTon, adiciona na lista
                        contadorTag.add(tagName);
                    } else if (fechamentoTag) { // verifica se é uma tag de fechamento
                        if (pilhaLista.estaVazia()) { // se possuir fechamento sem abrir, reporta erro
                            report.append("Erro linha ").append(numeroLinha)
                                  .append(": tag final </").append(tagName)
                                  .append("> sem correspondente de abertura.\n");
                            haveError = true;
                            position = fim + 1;
                            continue;
                        }
                        String lastOpened = pilhaLista.pop();
                        if (!lastOpened.equals(tagName)) { // verifica se a tag no topo da pilha é diferente da tag de fechamento

                            report.append("Erro linha ").append(numeroLinha)
                                  .append(": esperava </").append(lastOpened)
                                  .append(">, mas encontrou </").append(tagName).append(">\n");

                            haveError = true;

                            // Pega a tag no topo da pilha que é diferente da tag sendo lida
                            while (!pilhaLista.estaVazia() && !pilhaLista.peek().equals(tagName)) {
                                String missing = pilhaLista.pop();
                                report.append("Faltando tag final para <").append(missing).append(">\n");
                            }
                            if (!pilhaLista.estaVazia()) {
                                pilhaLista.pop(); // Remove a tag correspondente de fechamento da pilha
                            }
                            position = fim + 1;
                            continue;
                        }
                    } else {
                        pilhaLista.push(tagName); // adiciona tag na pilha para achar o fechamento
                        contadorTag.add(tagName); // adiciona a tag na lista de contador
                    }

                    position = fim + 1;
                }
            }

            // Verifica se ainda existem tags abertas sem fechamento
            while (!pilhaLista.estaVazia()) {
                report.append("Faltando tag final para <").append(pilhaLista.pop()).append(">\n");
                haveError = true;
            }

        } catch (Exception e) {
            report.append("Erro ao ler arquivo: ").append(e.getMessage()).append("\n");
            return false;
        }

        if (haveError) {
            return false;
        }
        report.append("Arquivo está bem formatado!\n");
        return true;
    }

    /// Encontra o fim de uma tag considerando aspas e atributos.
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

    /// Verifica se uma tag é do tipo singleton (auto-fechável).
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