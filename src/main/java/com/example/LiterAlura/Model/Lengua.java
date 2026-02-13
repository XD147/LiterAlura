package com.example.LiterAlura.Model;

public enum Lengua {
    es("es"),
    en("en"),
    fr("fr"),
    pt("pt");

    private String lenguatxt;

    Lengua (String lenguatxt){

        this.lenguatxt = lenguatxt;
    }

    public static Lengua fromString(String text) {
        for (Lengua lengua : Lengua.values()) {

            if (lengua.lenguatxt.equalsIgnoreCase(text)) {
                return lengua;
            }
        }
        throw new IllegalArgumentException("Sin lenguaje encontrado: " + text);
    }
}
