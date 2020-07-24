package com.springboot.app.personas.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.personas.modelo.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long>{

}
