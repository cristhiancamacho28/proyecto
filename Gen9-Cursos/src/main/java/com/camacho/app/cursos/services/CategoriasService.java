package com.camacho.app.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.dao.ICategoriasRepository;
import com.camacho.app.cursos.entities.Categoria;


@Component
public class CategoriasService implements ICategoriasService {
	
	@Autowired
	ICategoriasRepository categoriasRepo;
	

	@Override
	public List<Categoria> getAll() {
		return (List<Categoria>)categoriasRepo.findAll();
		
	}

	@Override
	public void save(Categoria categoria) {
		categoriasRepo.save(categoria);
		
	}

	@Override
	public Categoria getById(Long id) {
		// TODO Auto-generated method stub
		return categoriasRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		categoriasRepo.deleteById(id);
		
	}
	
	

}
