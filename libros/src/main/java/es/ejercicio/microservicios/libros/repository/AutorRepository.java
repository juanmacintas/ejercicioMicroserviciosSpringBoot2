package es.ejercicio.microservicios.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ejercicio.microservicios.libros.entity.Autor;





@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {


}