package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.recuperaConecxao();

		PreparedStatement stm = con.prepareStatement("DELETE FROM produto WHERE ID > ?");
		stm.setInt(1, 2);
		stm.execute();

		Integer res = stm.getUpdateCount();

		System.out.println("Foram alteradas " + res + " linhas");

		con.close();
		stm.close();
	}
}
