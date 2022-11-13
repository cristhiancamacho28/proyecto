package com.camacho.app.cursos.controllers;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.camacho.app.cursos.entities.Categoria;
import com.camacho.app.cursos.entities.Curso;
import com.camacho.app.cursos.entities.Instructor;

import com.camacho.app.cursos.services.ICursosService;


@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	
	@Autowired
	private ICursosService cursosService;
	
	@GetMapping(value = "/create")
	public String create(Model model) {
		Curso curso = new Curso();
		model.addAttribute("curso", curso);
		model.addAttribute("categorias", cursosService.getListCategorias());
		model.addAttribute("instructores", cursosService.getListInstructores());
		model.addAttribute("temarios", cursosService.getListTemarios());
		return "Cursos/create";			
	}
	
	
	@PostMapping(value = "/insert")
	public String insert(@Valid @ModelAttribute("curso")
	Curso curso,
	BindingResult result,
	Model model) {
	//comprobamos que el ususariio haya seleccionado
	//un temario para el tema
	//en caso de que no asociemos un mensaje que mande error para el atributo temario del objeto
		if (curso.getCategoria().getId() == null) {
			FieldError error = new FieldError("curso",
					"categoria",
					"Debes seleccionar una categoria");
			result.addError(error);
		}
		if (curso.getInstructor().getId() == null) {
			FieldError error = new FieldError("curso",
					"Instructor",
					"Debes seleccionar un instructor");
			result.addError(error);
		}
		if (curso.getTemario().getId() == null) {
			FieldError error = new FieldError("curso",
					"Temario",
					"Debes seleccionar un temario");
			result.addError(error);
		}
					
		if (result.hasErrors()) {
			model.addAttribute("categorias",
					cursosService.getListCategorias());
			model.addAttribute("instructores",
					cursosService.getListInstructores());
			model.addAttribute("temarios",
					cursosService.getListTemarios());			
			return "Cursos/create";
		}
		
		
		cursosService.save(curso);
		Instructor ins = cursosService
				.getInstructorById(curso.getInstructor().getId());
		ins.setHoras(ins.getHoras() + curso.getHorasDuracion());
		cursosService.save(ins);
		
		Categoria cat = cursosService
				.getCategoriaById(curso.getCategoria().getId());
		cat.setNumeroCursos(cat.getNumeroCursos() + 1);
		cursosService.save(cat);
		return "redirect:/cursos/listarAsync";
				
	}
	
	
	@GetMapping(value = "/listarAsync")
	public String listarAsync(Model model) throws InterruptedException {
		
		
		return "Cursos/indexAsync";
		
	}
	
	//api
	@GetMapping(value = "/api/listadoCursos",
			produces = {"application/json" })
	public @ResponseBody Map<String, List<Curso>> 
	         apiListar(Model model)
	         {
	    //throws InterruptedException{
		//Thread.sleep(9000);
		Map<String, List<Curso>> map = 
				new HashMap<String, List<Curso>>();
		map.put("data", cursosService.getAll());
		return map;
			
	}
}



