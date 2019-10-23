package br.com.servicos.produtos;

import java.net.URI;
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
	
	//buscar todos
	@GetMapping("/api/produtos")
	public List<Produto> retrieveAllProduto() {
		System.out.println("\n\n\nteste\n\n\n");
		return produtoRepository.findAll();
	}

//	@GetMapping(path="/api/produtos")
//	public @ResponseBody Iterable<Produto> getAllProdutos() {
//		System.out.println("\n\n\nteste\n\n\n");
//		return produtoRepository.findAll();
//	}
	
	//GET - lista produtos por id
	@GetMapping("/api/produtos/{id}")
	public Produto buscaProdutoPorId(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent())
			throw new RuntimeException("id-" + id);
		
		System.out.println("\n\n\nteste\n\n\n");
		return produto.get();
	}
	
	//POST - novo registro de produto
	// VER ESTE SITE PARA IMPLEMENTACAO
	// https://www.oracle.com/technetwork/pt/articles/dsl/crud-rest-sb2-hibernate-5302424-ptb.html
	@PostMapping("/produtos")
	public ResponseEntity<Object> createProduto(@RequestBody Produto produto) {
		Produto savedproduto = produtoRepository.save(produto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedproduto.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	
}