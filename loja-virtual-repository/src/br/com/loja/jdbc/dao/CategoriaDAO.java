package br.com.loja.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.jdbc.modelo.Categoria;
import br.com.loja.jdbc.modelo.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection con) {
		this.connection = con;
	}

	public List<Categoria> listar() throws SQLException {

		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT id, nome FROM categorias;";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					categorias.add(new Categoria(rst.getInt(1), rst.getString(2)));
				}
			}
		}
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		
		String sql = "SELECT c.id, c.nome, p.id, p.nome, p.descricao FROM categorias c "
				+ "JOIN produto p ON c.id = p.categoria_id;";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					}
					ultima.adiciona(new Produto(rst.getInt(3), rst.getString(4), rst.getString(5)));
				}
			}
		}
		return categorias;
	}
}
