package com.camacho.app.cursos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.entities.Temario;

@Component
public interface ITemariosRepository extends CrudRepository<Temario, Long>{

}
