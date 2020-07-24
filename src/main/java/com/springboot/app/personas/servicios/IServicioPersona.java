package com.springboot.app.personas.servicios;

import java.util.List;



import com.springboot.app.personas.modelo.Persona;

public interface IServicioPersona{
	public List<Persona> findAll();
	public Persona findById(Long id);
	public Persona save(Persona persona);
	public Persona editar(Persona persona,Long id);
	public void eliminar(Long id);
}
