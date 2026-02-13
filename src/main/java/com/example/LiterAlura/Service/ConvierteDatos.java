package com.example.LiterAlura.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T ObtieneRoot(String jargon) {
        try {
            return (T) objectMapper.readTree(jargon);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
