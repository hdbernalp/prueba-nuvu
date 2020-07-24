package com.springboot.app.personas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.personas.modelo.Persona;
import com.springboot.app.personas.servicios.IServicioPersona;



@RestController
@RequestMapping("persona")
public class PersonaController {
	
	@Autowired
	private IServicioPersona servicioPersona;
	
	@GetMapping("/listar")
	public List<Persona> listar(){
		return servicioPersona.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Persona buscarPersona(@PathVariable Long id){
		return servicioPersona.findById(id);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona crear(@RequestBody Persona persona){	
		return servicioPersona.save(persona);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona editar(@RequestBody Persona persona, @PathVariable Long id){
		return servicioPersona.editar(persona, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id){
		servicioPersona.eliminar(id);
	}

}
