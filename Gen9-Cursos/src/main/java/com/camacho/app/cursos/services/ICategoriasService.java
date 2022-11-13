package com.camacho.app.cursos.services;

import java.util.List;

import com.camacho.app.cursos.entities.Categoria;

public interface ICategoriasService {
	public List<Categoria> getAll();
	public void save(Categoria categoria);
	public Categoria getById(Long id);
	public void delete(Long id);
	

}
