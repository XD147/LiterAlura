package com.example.LiterAlura.Repository;

import com.example.LiterAlura.Model.Autor;
import com.example.LiterAlura.Model.Lengua;
import com.example.LiterAlura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByLanguages(Lengua idioma);

    @Query("SELECT a FROM Libro a JOIN a.autor e ORDER BY a.titulo ASC")
    List<Libro> listarLibro();

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> listarAutor();

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.libros WHERE a.deathYear > :hasta")
    List<Autor> listarVivos(int hasta);

}
