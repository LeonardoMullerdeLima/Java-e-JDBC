package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametros {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		try (Connection con = factory.recuperaConecxao()) {
			con.setAutoCommit(false);

			try (PreparedStatement stm = con.prepareStatement("INSERT INTO produto (NOME, DESCRICAO) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {

				insereProduto("SmartTV", "50 polegadas", stm);
				insereProduto("Radio", "Radio à pilha", stm);

				stm.close();
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				con.rollback();
			}
		}
	}

	private static void insereProduto(String nome, String desc, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, desc);

//		if (nome.equals("Radio")) {
//			throw new RuntimeException("Não foi possível adicionar o produto");
//		}

		stm.execute();

		try (ResultSet id = stm.getGeneratedKeys()) {
			while (id.next()) {
				System.out.println(id.getInt(1));
			}
		}
	}
}
