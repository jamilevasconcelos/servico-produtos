package br.com.servicos.produtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.*;

import br.com.servicos.produtos.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p.id, p.nome, p.qtdd, "
			+ "p.minima, p.maxima, p.fornecedor, p.valor "
			+ "FROM Produto AS p "
			+ "WHERE p.qtdd <= p.minima")
	List<Produto> findIfMinima( );

}
