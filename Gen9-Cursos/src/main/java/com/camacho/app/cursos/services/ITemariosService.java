package com.camacho.app.cursos.services;

import java.util.List;


import com.camacho.app.cursos.entities.Temario;

public interface ITemariosService {
	public List<Temario> getAll();
	public void save(Temario temario);
	public Temario getById(Long id);
	public void delete(Long id);
}

