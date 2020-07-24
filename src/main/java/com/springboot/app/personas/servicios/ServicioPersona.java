package com.springboot.app.personas.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.springboot.app.personas.dao.IPersonaDao;
import com.springboot.app.personas.modelo.Persona;

@Service
public class ServicioPersona implements IServicioPersona{

	@Autowired
	IPersonaDao personaDao;
	
	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return (List<Persona>)personaDao.findAll();
	}

	@Override
	public Persona findById(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}

	@Override
	public Persona save(Persona persona) {
		// TODO Auto-generated method stub
		return personaDao.save(persona);
	}

	@Override
	public Persona editar(Persona persona, Long id) {
		// TODO Auto-generated method stub
		Persona aux=personaDao.findById(id).orElse(null);
		if(aux!=null) {
			aux.setNombre(persona.getNombre());
			aux.setApellido(persona.getApellido());
			aux.setNumTarjeta(persona.getNumTarjeta());
			aux.setEmail(persona.getEmail());
			return personaDao.save(aux);			
		};
		return null;
	}

	@Override
	public void eliminar(Long id) {
		personaDao.deleteById(id);		
	}

}
