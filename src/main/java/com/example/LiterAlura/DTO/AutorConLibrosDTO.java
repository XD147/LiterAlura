package com.example.LiterAlura.DTO;

import java.util.List;

public record AutorConLibrosDTO(
        String nombreAutor,
        Integer fechaNacimiento,
        Integer fechaFallecimiento,
        List<String> libros
) {

    public String formatoImpresion() {
        return String.format(
                "\n...::: AUTOR :::...\n"+
                "Nombre autor: %s%n" +
                "Fecha de nacimiento: %s%n" +
                "Fecha de fallecimiento: %s%n" +
                "Libros: [%s]%n",
                nombreAutor,
                fechaNacimiento,
                fechaFallecimiento,
                String.join(", ", libros)
        );
    }
}
