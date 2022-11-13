package com.camacho.app.cursos.services;

import java.util.List;


import com.camacho.app.cursos.entities.Instructor;

public interface IInstructoresService {
	
	public List<Instructor> getAll();
	public void save(Instructor instructor);
	public Instructor getById(Long id);
	public void delete(Long id);

}
