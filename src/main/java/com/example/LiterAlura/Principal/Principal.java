package com.example.LiterAlura.Principal;

import com.example.LiterAlura.DTO.AutorConLibrosDTO;
import com.example.LiterAlura.Model.Autor;
import com.example.LiterAlura.Model.DatosLibro;
import com.example.LiterAlura.Model.Lengua;
import com.example.LiterAlura.Model.Libro;
import com.example.LiterAlura.Repository.LibroRepository;
import com.example.LiterAlura.Service.ConsumoAPI;
import com.example.LiterAlura.Service.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    @Autowired
    private LibroRepository repositorio;

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=%20";

    private ConvierteDatos conversor = new ConvierteDatos();

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n...::: Bienvenidos a LiterAlura :::...
                    --------------------------------------
                    Elija la opción a través de su número:
                    
                        1 - Buscar libro por titulo 
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos a un determinado año
                        5 - Listar libros por idioma
                                                          
                        0 - Salir
                    --------------------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarVivos();
                    break;
                case 5:
                    listarIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "%20"));
        System.out.println(json);
        JsonNode jsonNodeRoot = conversor.ObtieneRoot(json);
        var nd = jsonNodeRoot.get("results").get(0).toString();
        DatosLibro datos = conversor.obtenerDatos(nd, DatosLibro.class);
        return datos;
    }


    private void listarLibros() {
      var libros = repositorio.listarLibro();
      if (!libros.isEmpty()){
          libros.forEach(l -> System.out.println(l.toString()));
      }
      else {
          System.out.println("Aun no hay libros para listar!");
      }
    }

    private void buscarLibro() {

        try {
            DatosLibro datos = getDatosLibro();
            Libro libro = new Libro(datos);
            repositorio.save(libro);
            System.out.println(libro.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

    }

    private void listarAutores() {
        var result = repositorio.listarAutor()
                .stream()
                .collect(Collectors.groupingBy(
                        Autor::getName,
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .values()
                .stream()
                .map(autores -> {

                    Autor autor = autores.get(0);

                    List<String> libros = autores.stream()
                            .flatMap(a -> a.getLibros().stream())
                            .map(Libro::getTitulo)
                            .distinct()
                            .sorted() // opcional: orden alfabético
                            .toList();

                    return new AutorConLibrosDTO(
                            autor.getName(),
                            autor.getBirthYear(),
                            autor.getDeathYear(),
                            libros
                    );
                })
                .toList();
        result.forEach(a-> System.out.println(a.formatoImpresion()));
    }

    private void listarVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar: ");
        var ano = teclado.nextInt();
        teclado.nextLine();

        var resulta = repositorio.listarVivos(ano)
                .stream()
                .collect(Collectors.groupingBy(
                        Autor::getName,
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .values()
                .stream()
                .map(autores -> {

                    Autor autor = autores.get(0);

                    List<String> libros = autores.stream()
                            .flatMap(a -> a.getLibros().stream())
                            .map(Libro::getTitulo)
                            .distinct()
                            .sorted() // opcional: orden alfabético
                            .toList();

                    return new AutorConLibrosDTO(
                            autor.getName(),
                            autor.getBirthYear(),
                            autor.getDeathYear(),
                            libros
                    );
                })
                .toList();
        resulta.forEach(a-> System.out.println(a.formatoImpresion()));
    }


    private void listarIdioma() {
        var menu = """
                    \nIngrese el idioma para buscar los libros:
                    
                        es - Español 
                        en - Inglés
                        fr - Francés
                        pt - Portugués

                    --------------------------------------
                    """;
        System.out.println(menu);
        var lengua = teclado.nextLine();

        try {
            Lengua idioma = Lengua.fromString(lengua);

            var listaIdioma = repositorio.findByLanguages(idioma);

            if (!listaIdioma.isEmpty()){
                listaIdioma.forEach(l -> System.out.println(l.toString()));
            }
            else {
                System.out.println("No hay libros en el idioma ingresado!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
