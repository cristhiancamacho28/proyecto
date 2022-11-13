package com.camacho.app.cursos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.entities.Instructor;

@Component
public interface IInstructoresRepository extends CrudRepository<Instructor, Long> {

}
