package com.camacho.app.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camacho.app.cursos.dao.ITemariosRepository;
import com.camacho.app.cursos.dao.ITemasRepository;
import com.camacho.app.cursos.entities.Tema;
import com.camacho.app.cursos.entities.Temario;

@Service
public class TemasService implements ITemasService {
	
	@Autowired
	private ITemasRepository temasRepository;
	
	@Autowired
	private ITemariosRepository temariosRepository;
	

	@Override
	public List<Tema> getAll() {
		return (List<Tema>) temasRepository.findAll();
	}

	@Override
	public void save(Tema tema) {
		temasRepository.save(tema);
		
	}

	@Override
	public Tema getById(Long id) {
		return temasRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		temasRepository.deleteById(id);
	
	}

	@Override
	public void save(Temario temario) {
		// TODO Auto-generated method stub
		temariosRepository.save(temario);
		
	}

	@Override
	public List<Temario> getListTemarios() {
		// TODO Auto-generated method stub
		return (List<Temario>)temariosRepository.findAll();	
	}

	@Override
	public Temario getTemarioById(Long id) {
		// TODO Auto-generated method stub
		return temariosRepository.findById(id).orElse(null);
	}
	
}