package com.adauto.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.adauto.cursomc.domain.Categoria;

import com.adauto.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) //adicionando um endpont a url para acessar o categorias/id

	public ResponseEntity<?> find(@PathVariable Integer id) { //completando o de cima

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}
}
