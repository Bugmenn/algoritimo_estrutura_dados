package Trabalho.src.utils;

public class Util {
    public static String extrairTagName(String tag) {
        return tag.replace("/", "").split(" ")[0].toLowerCase();
    }
}