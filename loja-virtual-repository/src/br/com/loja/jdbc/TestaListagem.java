package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();

		try {
			con = connectionFactory.recuperaConecxao();

			PreparedStatement stm = con.prepareStatement("SELECT * FROM produto");
			stm.execute();

			ResultSet res = stm.getResultSet();

			while (res.next()) {
				Integer id = res.getInt(1);
				String nome = res.getString(2);
				String desc = res.getString(3);
				System.out.println(id + ", " + nome + ", " + desc);
			}

			stm.close();
			res.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
			System.out.println("\nConexão encerrada.");
		}
	}
}
