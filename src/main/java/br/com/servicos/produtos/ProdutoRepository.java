package br.com.servicos.produtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

import br.com.servicos.produtos.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
