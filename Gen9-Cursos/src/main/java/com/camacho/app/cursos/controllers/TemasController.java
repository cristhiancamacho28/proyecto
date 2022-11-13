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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.camacho.app.cursos.entities.Tema;
import com.camacho.app.cursos.entities.Temario;
import com.camacho.app.cursos.services.ITemasService;




@Controller
@RequestMapping("/temas")
public class TemasController {
	
	@Autowired
	private ITemasService temasService;
	
	@GetMapping(value = "/create")
	public String create(Model model) {
		Tema tema = new Tema();
		model.addAttribute("tema", tema);
		List<Temario> temarios = temasService.getListTemarios();
		model.addAttribute("temarios", temarios);
		return "Temas/create";	
		
	}
	
	
	@PostMapping(value = "/insert")
	public String insert(@Valid @ModelAttribute("tema")
	Tema tema,
	BindingResult result,
	Model model) {
	//comprobamos que el ususariio haya seleccionado
	//un temario para el tema
	//en caso de que no asociemos un mensaje que mande error para el atributo temario del objeto
		if (tema.getTemario().getId() == null) {
			FieldError error = new FieldError("tema",
					"temario",
					"Debes seleccionar un temario para el tema");
			result.addError(error);
		}
		if (result.hasErrors()) {
			model.addAttribute("temarios",
					temasService.getListTemarios());
			return "Temas/create";
		}
		temasService.save(tema);
		
		Temario temario = temasService
				.getTemarioById(tema.getTemario().getId());
		temario.setNumTemas(temario.getNumTemas()+ 1);
		temasService.save(temario);
		return "redirect:/temas/listarAsync";
				
	}
	
	@GetMapping(value="/listarAsync")
	public String listarAsync(Model model) throws InterruptedException{
		return "Temas/index";
	}
	
	
	
	
	@GetMapping(value="api/listadoTemas",
			produces = { "application/json"})
	/*api para edvolver la lista de temas*/
	public @ResponseBody Map<String, List<Tema>> apiListar()
	throws InterruptedException{
		//thread.sleep(9000);
		Map<String, List<Tema>> map = 
				new HashMap<String, List<Tema>>();
		map.put("data", temasService.getAll());
		return map;
	}
	
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable(value="id") Long id,
			Model model)
	{
		Tema tema = null;
		if (id > 0) {
			//recuperamos el objeto categoria que queremos editar
			tema = temasService.getById(id);
			if (tema == null) {
				return "redirect:/temas/listar";
			}
		}
		else {
			return "redirect:/temas/listar";
		}
		//le pasamos el objeto categorias a la vista
		model.addAttribute("tema", tema);
		return "temas/create";
		
	}
	
	
	@DeleteMapping(value = "/api/delete/{id}",
			produces = {"application/json" })
	public @ResponseBody Map<String, String> apiDelete(
			@PathVariable(value = "id")
			Long id, Model model
			){
		temasService.delete(id);
		
		Map<String , String> resultados =
				new HashMap<String, String>();
		
		resultados.put("success", "true");
		resultados.put("message",
				"Tema borrada correctamente");
		return resultados;
		
	}
}
