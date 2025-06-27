package Trabalho.src.utils;

public class Util {

    /// Pega apenas a parte do texto da tag
    public static String extractTagName(String tag) {
        return tag.replace("/", "").split(" ")[0].toLowerCase();
    }
}