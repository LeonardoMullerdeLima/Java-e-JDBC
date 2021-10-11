package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.loja.jdbc.dao.ProdutoDAO;
import br.com.loja.jdbc.modelo.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {

		Produto comoda = new Produto("Comoda", "Comoda vertical");

		try (Connection con = new ConnectionFactory().recuperaConecxao()) {

			ProdutoDAO persistenciaProduto = new ProdutoDAO(con);
			persistenciaProduto.salvar(comoda);
			List<Produto> listaDeProdutos = persistenciaProduto.listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}
	}
}
