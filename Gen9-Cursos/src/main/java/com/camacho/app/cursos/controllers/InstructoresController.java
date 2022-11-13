package com.camacho.app.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.camacho.app.cursos.entities.Instructor;

import com.camacho.app.cursos.services.IInstructoresService;


@Controller
@RequestMapping("/instructores")
public class InstructoresController {
	@Autowired
	private IInstructoresService instructoresService;
	
	/*@GetMapping(value = "/listar")
	public String listar(Model model) throws InterruptedException {
		Thread.sleep(5000);
		//regresamos la lista de categorias
		List<Instructor> instructores = instructoresService.getAll();
		//a travez del objeto model, le pasamos la lista de categorias
		
		model.addAttribute("instructores", instructores);
		return "Instructores/index";
	
	}*/
	
	@GetMapping(value = "/listarAsync")
	public String listarAsync(Model model) throws InterruptedException {
		
		
		return "Instructores/indexAsync";
		
	}
	
	//api
	@GetMapping(value = "/api/listadoInstructores",
			produces = {"application/json" })
	/* api para devolver la lista de categorias*/
	public @ResponseBody Map<String, List<Instructor>> apiListar()
	        throws InterruptedException{
		Thread.sleep(9000);
		Map<String, List<Instructor>> map = 
				new HashMap<String, List<Instructor>>();
		map.put("data", instructoresService.getAll());
		return map;
			
	}
	@DeleteMapping(value = "/api/delete/{id}",
			produces = {"application/json" })
	public @ResponseBody Map<String, String> apiDelete(
			@PathVariable(value = "id")
			Long id, Model model
			){
		instructoresService.delete(id);
		
		Map<String , String>resultados =
				new HashMap<String, String>();
		
		resultados.put("success", "true");
		resultados.put("message",
				"Instructor borrado correctamente");
		return resultados;
		
	}
	
	@GetMapping(value = "/create")
	public String create(Model model) {
		//creanos el objeto con el que vamos a trabakjar la lista
		Instructor instructor = new Instructor();
		
		//pasamos el objeto categoria a la vista
		model.addAttribute("instructor", instructor);
		return "Instructores/create";
	}
	
	
	@PostMapping(value="/insert")
	public String insert(@Valid @ModelAttribute("instructor")
	Instructor instructor,
	BindingResult result) {
		if(result.hasErrors()) {
			return "Instructores/create";
		}
		
		if( ( instructor.getId() == null)) {
			instructor.setHoras((double)0);			
		}
		else {
			instructor.setHoras(instructoresService.getById(instructor.getId()).getHoras());
		}
		
		instructoresService.save(instructor);
		return "redirect:/instructores/listarAsync";
				
	}
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable(value="id") Long id,
			Model model)
	{
		Instructor instructor = null;
		if (id > 0) {
			//recuperamos el objeto categoria que queremos editar
			instructor = instructoresService.getById(id);
			if (instructor == null) {
				return "redirect:/instructores/listar";
			}
		}
		else {
			return "redirect:/instructores/listar";
		}
		//le pasamos el objeto categorias a la vista
		model.addAttribute("instructor", instructor);
		return "Instructores/create";
		
	}
		
}
