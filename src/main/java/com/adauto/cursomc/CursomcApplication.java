package com.adauto.cursomc;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adauto.cursomc.domain.Categoria;
import com.adauto.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriarepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { //instanciando objetos para salvar no bd
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2));
	}

}
