package com.example.LiterAlura.Service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);

    <T> T ObtieneRoot(String jargon);
}