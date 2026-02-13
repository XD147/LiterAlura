package com.example.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autor;

    @Enumerated(EnumType.STRING)
    private Lengua languages;

    private Integer descargas;

    public Libro() {
    }

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autor = datos.autores().stream().map(a -> new Autor(a.name(), a.birthYear(), a.deathYear())).toList();
        this.languages = Lengua.fromString(datos.lenguaje().toString().replace("[", "").replace("]", ""));
        this.descargas = datos.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public Lengua getLanguages() {
        return languages;
    }

    public void setLanguages(Lengua languages) {
        this.languages = languages;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "\n...::: LIBRO :::...\n" +
                "Titulo: " + titulo + '\n' +
                "Autor: " + autor.getFirst().getName() + '\n' +
                "Idioma: " + languages +'\n' +
                "N° descargas: " + descargas + '\n' +
                "-------------------";
    }
}
