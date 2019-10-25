package br.com.servicos.produtos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//		 ResponseEntity.created(location).build();
		 return ResponseEntity.status(HttpStatus.OK).body("Item incluído com sucesso!");
	}


	// checar se quantidade do produto está abaixo da quantidade minima
	@GetMapping("/api/produtos/checarsequantidademinima")
	public void checarSeQuantidadeMinima() throws IOException {
		
		List<Produto> todosprodutos = new ArrayList<Produto>(produtoRepository.findAll());		

		URL url = new URL("https://compra.free.beeceptor.com");

		for (int i = 0; i < todosprodutos.size(); i++) {
			Produto produto = todosprodutos.get(i);
			if  (produto.getQtdd() <= produto.getMinima()) {
			
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json; utf-8");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);
				
				String jsonInputString = "{'datahora': " + LocalDateTime.now() + " 'produto': " 
										 + produto.getId() + ", 'fornecedor': " 
										 + produto.getFornecedor() + " '}";

				ResponseEntity.status(HttpStatus.OK).body("Produto requisitado:" + produto.getNome());

				try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(con.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
					

				    System.out.println(response.toString());
				}
			}
		}	
	}
}