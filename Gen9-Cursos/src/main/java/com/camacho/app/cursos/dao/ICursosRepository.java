package com.camacho.app.cursos.dao;

import org.springframework.data.repository.CrudRepository;

import com.camacho.app.cursos.entities.Curso;

public interface ICursosRepository  extends CrudRepository<Curso, Long>  {

}
