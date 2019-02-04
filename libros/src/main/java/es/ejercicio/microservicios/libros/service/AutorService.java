package es.ejercicio.microservicios.libros.service;

import java.util.List;

import es.ejercicio.microservicios.libros.entity.Autor;

public interface AutorService {

    public List<Autor> findAll();

    public Autor findById(Integer id);

    public void deleteById(Integer id);

    public Autor nuevoAutor(Autor autor);
}