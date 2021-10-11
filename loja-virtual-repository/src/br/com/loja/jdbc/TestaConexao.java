package br.com.loja.jdbc;

import java.sql.Connection;

public class TestaConexao {

	public static void main(String[] args) throws Exception {
		Connection con = null;
		ConnectionFactory connectionFactory = new ConnectionFactory();

		try {
			con = connectionFactory.recuperaConecxao();
			System.out.println("yay");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
			System.out.println("bye");
		}
	}
}
