package br.com.servicos.produtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	public String nome;
	public int qtdd;
	public int minima;
	public int maxima;
	public int fornecedor;
	public double valor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtdd() {
		return qtdd;
	}
	public void setQtdd(int qtdd) {
		this.qtdd = qtdd;
	}
	public int getMinima() {
		return minima;
	}
	public void setMinima(int minima) {
		this.minima = minima;
	}
	public int getMaxima() {
		return maxima;
	}
	public void setMaxima(int maxima) {
		this.maxima = maxima;
	}
	public int getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(int fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	
//	//implementar
//	public boolean checaSeQuantidadeMinima(){
//			return getQtdd() <= getMinima();
//	}
	
	
}