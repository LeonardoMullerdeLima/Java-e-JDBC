package br.com.loja.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void adiciona(Produto produto) {
		produtos.add(produto);
	}
	
	@Override
	public String toString() {
		return String.format("Categoria: %d | %s", this.id, this.nome);
	}
}
