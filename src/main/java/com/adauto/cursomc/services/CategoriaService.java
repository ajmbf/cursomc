package com.adauto.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adauto.cursomc.domain.Categoria;
import com.adauto.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) { 
	 	Optional<Categoria> obj = repo.findById(id); 
	 	return obj.orElseThrow(() -> new ObjectNotFoundException( 
	 	 	 	"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null)); } 


}