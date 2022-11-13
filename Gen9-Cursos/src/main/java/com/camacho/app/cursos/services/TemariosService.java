package com.camacho.app.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.dao.ITemariosRepository;

import com.camacho.app.cursos.entities.Temario;


@Component
public class TemariosService implements ITemariosService {

	@Autowired
	ITemariosRepository temariosRepo;

	@Override
	public List<Temario> getAll() {
		return (List<Temario>) temariosRepo.findAll();
	}

	@Override
	public void save(Temario temario) {
		temariosRepo.save(temario);
		
	}

	@Override
	public Temario getById(Long id) {
		return temariosRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		temariosRepo.deleteById(id);
		
	}

}
