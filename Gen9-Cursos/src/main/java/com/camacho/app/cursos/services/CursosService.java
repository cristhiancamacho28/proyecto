package com.camacho.app.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camacho.app.cursos.dao.ICategoriasRepository;
import com.camacho.app.cursos.dao.ICursosRepository;
import com.camacho.app.cursos.dao.IInstructoresRepository;
import com.camacho.app.cursos.dao.ITemariosRepository;
import com.camacho.app.cursos.entities.Categoria;
import com.camacho.app.cursos.entities.Curso;
import com.camacho.app.cursos.entities.Instructor;
import com.camacho.app.cursos.entities.Temario;


@Service
public class CursosService implements ICursosService{
	
	@Autowired
	private ICategoriasRepository categoriasRepository;
	@Autowired
	private IInstructoresRepository instructoresRepository;
	@Autowired
	private ITemariosRepository temariosRepository;
	@Autowired
	private ICursosRepository cursosRepository;
	
	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return (List<Curso>) cursosRepository.findAll();
	}

	@Override
	public void save(Curso curso) {
		// TODO Auto-generated method stub
		cursosRepository.save(curso);
	}

	@Override
	public Curso getById(Long id) {
		// TODO Auto-generated method stub
		return cursosRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cursosRepository.deleteById(id);
	}

	@Override
	public void save(Categoria categoria) {
		// TODO Auto-generated method stub
		categoriasRepository.save(categoria);
	}

	@Override
	public void save(Instructor instructor) {
		// TODO Auto-generated method stub
		instructoresRepository.save(instructor);
	}

	@Override
	public List<Categoria> getListCategorias() {
		// TODO Auto-generated method stub
		return (List<Categoria>)categoriasRepository.findAll();
	}

	@Override
	public List<Instructor> getListInstructores() {
		// TODO Auto-generated method stub
		return (List<Instructor>)instructoresRepository.findAll();
	}

	@Override
	public List<Temario> getListTemarios() {
		// TODO Auto-generated method stub
		return (List<Temario>)temariosRepository.findAll();
	}

	@Override
	public Categoria getCategoriaById(Long id) {
		// TODO Auto-generated method stub
		return categoriasRepository.findById(id).orElse(null);
	}

	@Override
	public Instructor getInstructorById(Long id) {
		// TODO Auto-generated method stub
		return instructoresRepository.findById(id).orElse(null);
	}
			
}
	