package com.camacho.app.cursos.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camacho.app.cursos.dao.IInstructoresRepository;
import com.camacho.app.cursos.entities.Instructor;


@Component
public class InstructoresService implements IInstructoresService {
	
	@Autowired
	IInstructoresRepository instructoresRepo;
	

	@Override
	public List<Instructor> getAll() {
		return (List<Instructor>)instructoresRepo.findAll();
		
	}

	@Override
	public void save(Instructor instructor) {
		instructoresRepo.save(instructor);
		
	}

	@Override
	public Instructor getById(Long id) {
		// TODO Auto-generated method stub
		return instructoresRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		instructoresRepo.deleteById(id);
		
	}
	
	

}
