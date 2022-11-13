package com.camacho.app.cursos.services;

import java.util.List;

import com.camacho.app.cursos.entities.Tema;
import com.camacho.app.cursos.entities.Temario;

public interface ITemasService {
	
	public List<Tema> getAll();
	public void save(Tema tema);
	public Tema getById(Long id);
	public void delete(Long id);
	
	
	public void save(Temario temario);
	public List<Temario>getListTemarios();
	public Temario getTemarioById(Long id);

}
