package br.com.servicos.produtos;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.servicos.produtos.Produto;
import br.com.servicos.produtos.ProdutoRepository;


@RestController
public class ProdutoRestController {
	
	@Autowired	
	public ProdutoRepository produtoRepository;
	
	@GetMapping(value="/hello") 	
	public String hello (){
		return "Hello Mundo";
	}
	
	// buscar todos produtos
	@GetMapping("/api/produtos")
	public List<Produto> buscaTodosProduto() {
		return produtoRepository.findAll();
	}
	
	// buscar produtos por id
	@GetMapping("/api/produtos/{id}")
	public Produto buscaProdutoPorId(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent())
			throw new RuntimeException("id-" + id);
		
		return produto.get();
	}

	// cadastrar novo produto 
	@PostMapping("/api/produtos")
	public ResponseEntity<Object> novoProduto(@RequestBody Produto produto) {
		Produto savedproduto = produtoRepository.save(produto);
		System.out.println("teste");
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedproduto.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

//	//buscar por nome
//	@GetMapping("/api/produtos/checarsequantidademinima")
//	public List<Produto> retrieveAllAgendabyNome() {
//		System.out.println("Necessário repor estoque para o item: ");				
//		return produtoRepository.findIfMinima();
//
//	}
//
//	// checar se atingiu quantidade minima
//	@GetMapping("/api/produtos/checarsequantidademinima")
//	public  List<Produto> checarSeQuantidadeMinima() {
//		
//		List<Produto> todosprodutos = new ArrayList<Produto>(produtoRepository.findAll());		
//		List<Produto> comprasnecessarias = new ArrayList<Produto>();
//				
//		for (int i = 0; i < todosprodutos.size(); i++) {
//			if  (todosprodutos.getQtdd() <= (todosprodutos.getMinima())) {
//				comprasnecessarias.add(todosprodutos[i]);
//				System.out.println("Necessário repor estoque para o item: ");				
//
//			}
//		}	
//		return comprasnecessarias;		
//	}
//	
	
	
}