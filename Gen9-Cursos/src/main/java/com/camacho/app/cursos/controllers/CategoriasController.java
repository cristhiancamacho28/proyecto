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

import com.camacho.app.cursos.entities.Categoria;
import com.camacho.app.cursos.services.ICategoriasService;


@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	@Autowired
	private ICategoriasService categoriasService;
	
	@GetMapping(value = "/listar")
	public String listar(Model model) throws InterruptedException {
		Thread.sleep(5000);
		//regresamos la lista de categorias
		List<Categoria> categorias = categoriasService.getAll();
		//a travez del objeto model, le pasamos la lista de categorias
		
		model.addAttribute("categorias", categorias);
		return "Categorias/index";
	
	
	}
	
	
	@GetMapping(value = "/listarAsync")
	public String listarAsync(Model model) throws InterruptedException {
		
		
		return "Categorias/indexAsync";
		
	}
	
	//api
	@GetMapping(value = "/api/listadoCategorias",
			produces = {"application/json" })
	/* api para devolver la lista de categorias*/
	public @ResponseBody Map<String, List<Categoria>> apiListar()
	        throws InterruptedException{
		Thread.sleep(9000);
		Map<String, List<Categoria>> map = 
				new HashMap<String, List<Categoria>>();
		map.put("data", categoriasService.getAll());
		return map;
			
	}
	
	@DeleteMapping(value = "/api/delete/{id}",
			produces = {"application/json" })
	public @ResponseBody Map<String, String> apiDelete(
			@PathVariable(value = "id")
			Long id, Model model
			){
		categoriasService.delete(id);
		
		Map<String , String> resultados =
				new HashMap<String, String>();
		
		resultados.put("success", "true");
		resultados.put("message",
				"Categoria borrada correctamente");
		return resultados;
		
	}
	
	
	
	@GetMapping(value = "/create")
	public String create(Model model) {
		//creanos el objeto con el que vamos a trabakjar la lista
		Categoria categoria = new Categoria();
		
		//pasamos el objeto categoria a la vista
		model.addAttribute("categoria", categoria);
		return "Categorias/create";
	}
	
	
	@PostMapping(value="/insert")
	public String insert(@Valid @ModelAttribute("categoria")
	Categoria categoria,
	BindingResult result) {
		if(result.hasErrors()) {
			return "Categorias/create";
		}
		
		if( ( categoria.getId() == null)) {
			categoria.setNumeroCursos(0);			
		}
		else {
			categoria.setNumeroCursos(categoriasService.getById(categoria.getId()).getNumeroCursos());
		}
		
		categoriasService.save(categoria);
		return "redirect:/categorias/listarAsync";
				
	}
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable(value="id") Long id,
			Model model)
	{
		Categoria categoria = null;
		if (id > 0) {
			//recuperamos el objeto categoria que queremos editar
			categoria = categoriasService.getById(id);
			if (categoria == null) {
				return "redirect:/categorias/listar";
			}
		}
		else {
			return "redirect:/categorias/listar";
		}
		//le pasamos el objeto categorias a la vista
		model.addAttribute("categoria", categoria);
		return "Categorias/create";
		
	}
}
