package br.com.loja.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.jdbc.modelo.Categoria;
import br.com.loja.jdbc.modelo.Produto;

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO produto (NOME, DESCRICAO) VALUES (?, ?);";

		try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<>();

		String sql = "SELECT ID, NOME, DESCRICAO FROM produto;";

		try (PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {

					produtos.add(new Produto(rst.getInt(1), rst.getString(2), rst.getString(3)));
				}
			}
		}
		return produtos;
	}

	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT ID, NOME, DESCRICAO FROM produto WHERE categoria_id = ?;";

		try (PreparedStatement pstm = con.prepareStatement(sql)) {
			pstm.setInt(1, ct.getId());
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {

					produtos.add(new Produto(rst.getInt(1), rst.getString(2), rst.getString(3)));
				}
			}
		}
		return produtos;
	}
}
