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

import com.camacho.app.cursos.entities.Temario;
import com.camacho.app.cursos.services.ITemariosService;

@Controller
@RequestMapping("/temarios")
public class TemariosController {
	
	@Autowired
	private ITemariosService temariosService;
	
	/*@GetMapping(value="/listar")
	public String listar(Model model) throws InterruptedException {
		Thread.sleep(5000);
		
		List<Temario> temarios = temariosService.getAll();
		
		model.addAttribute("temarios", temarios);
		return "Temarios/indexTemario";
	}*/
	
	@GetMapping(value = "/listarAsync")
	public String listarAsync(Model model) throws InterruptedException {
	
	return "Temarios/indexAsync";
  }
	//apis 
	@GetMapping(value="/api/listadoTemarios",
			produces = {"application/json"})
	public @ResponseBody Map<String, List<Temario>> apiListar()
			throws InterruptedException{
		Thread.sleep(9000);
		Map<String, List<Temario>> map=
				new HashMap<String, List<Temario>>();
		map.put("data", temariosService.getAll());
		return map;
	}
	
	@DeleteMapping(value = "/api/delete/{id}",
			produces = {"application/json" })
	public @ResponseBody Map<String, String> apiDelete(
			@PathVariable(value = "id")
			Long id, Model model
			) {
		temariosService.delete(id);
		
		Map<String, String> resultados =
				new HashMap<String, String>();
		
		resultados.put("success", "true");
		resultados.put("message",
				"temario borrado correctamente");
		
		return resultados;
	}
	//fin de apis
	
	@GetMapping(value = "/create")
	public String create(Model model) {
		//creanos el objeto con el que vamos a trabakjar la lista
		Temario temario = new Temario();
		
		//pasamos el objeto temario a la vista
		model.addAttribute("temario", temario);
		return "Temarios/create";
	}
	
	@PostMapping(value="/insert")
	public String insert(@Valid @ModelAttribute("temario")
	Temario temario,
	BindingResult result) {
		if(result.hasErrors()) {
			return "/create";
		}
		
		if( ( temario.getId() == null)) {
			temario.setNumTemas(0);			
		}
		else {
			temario.setNumTemas(temariosService.getById(temario.getId()).getNumTemas());
		}
		
		temariosService.save(temario);
		return "redirect:/temarios/listarAsync";
				
	}
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable(value="id") Long id,
			Model model)
	{
		Temario temario = null;
		if (id > 0) {
			//recuperamos el objeto categoria que queremos editar
			temario = temariosService.getById(id);
			if (temario == null) {
				return "redirect:/temarios/listar";
			}
		}
		else {
			return "redirect:/temarios/listar";
		}
		//le pasamos el objeto categorias a la vista
		model.addAttribute("temario", temario);
		return "Temarios/create";
		
	}
	
}

