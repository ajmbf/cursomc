package com.adauto.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adauto.cursomc.domain.Categoria;
import com.adauto.cursomc.domain.Cidade;
import com.adauto.cursomc.domain.Cliente;
import com.adauto.cursomc.domain.Endereco;
import com.adauto.cursomc.domain.Estado;
import com.adauto.cursomc.domain.Pagamento;
import com.adauto.cursomc.domain.PagamentoComBoleto;
import com.adauto.cursomc.domain.PagamentoComCartao;
import com.adauto.cursomc.domain.Pedido;
import com.adauto.cursomc.domain.Produto;
import com.adauto.cursomc.domain.enums.EstadoPagamento;
import com.adauto.cursomc.domain.enums.TipoCliente;
import com.adauto.cursomc.repositories.CategoriaRepository;
import com.adauto.cursomc.repositories.CidadeRepository;
import com.adauto.cursomc.repositories.ClienteRepository;
import com.adauto.cursomc.repositories.EnderecoRepository;
import com.adauto.cursomc.repositories.EstadoRepository;
import com.adauto.cursomc.repositories.PagamentoRepository;
import com.adauto.cursomc.repositories.PedidoRepository;
import com.adauto.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { //instanciando objetos para salvar no bd
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado("Minas Gerais", null);
		Estado est2 = new Estado("São Paulo", null);
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2); //associacao muitos pra um, pois a gnt n precisa dar aqueles add array de cima
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2)); //respeitando a hierarquia de quem tem q ser salvo primeiro
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "361458795", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2341245121","12314124"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Ap 301", "Jardim", "51020480", cli1, c1);
		Endereco e2 = new Endereco(null, "Av mattos", "33", "Sala 800", "Centro", "265652", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/06/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	}

}
