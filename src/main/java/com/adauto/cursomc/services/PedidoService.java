package com.adauto.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adauto.cursomc.domain.Pedido;
import com.adauto.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) { 
	 	Optional<Pedido> obj = repo.findById(id); 
	 	return obj.orElseThrow(() -> new ObjectNotFoundException( 
	 	 	 	"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null)); } 


}