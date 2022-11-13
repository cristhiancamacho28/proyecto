package com.camacho.app.cursos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.entities.Categoria;

@Component
public interface ICategoriasRepository extends CrudRepository<Categoria, Long>{

}
