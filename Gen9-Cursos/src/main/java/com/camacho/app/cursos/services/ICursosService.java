package com.camacho.app.cursos.services;
import java.util.List;
import com.camacho.app.cursos.entities.Categoria;
import com.camacho.app.cursos.entities.Curso;
import com.camacho.app.cursos.entities.Instructor;
import com.camacho.app.cursos.entities.Temario;


public interface ICursosService {
	
	public List<Curso> getAll();
	public void save(Curso curso);
	public Curso getById(Long id);
	public void delete(Long id);
	
	public void save(Categoria categoria);
	public void save(Instructor instructor);
	
	public List<Categoria>getListCategorias();
	public List<Instructor>getListInstructores();
	public List<Temario>getListTemarios();
	
	public Categoria getCategoriaById(Long id);
	public Instructor getInstructorById(Long id);

	
}
