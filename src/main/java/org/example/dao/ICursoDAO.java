package org.example.dao;

import org.example.model.Area;
import org.example.model.Cursos;

import java.util.List;
import java.util.Optional;

public interface ICursoDAO {
    Cursos create(Cursos curso);
    void update(Cursos curso);
    void delete(Cursos curso);
    Optional<Cursos> findById(Long id);
    List<Cursos> findAll();
    List<Cursos> findByArea(Area area);
    Optional<Cursos> findBySigla(String sigla);
}