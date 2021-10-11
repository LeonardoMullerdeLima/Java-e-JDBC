package br.com.loja.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory conFac = new ConnectionFactory();
		Connection con = conFac.recuperaConecxao();

		Statement stm = con.createStatement();
		stm.execute("INSERT INTO produto (NOME, DESCRICAO) VALUES ('Mouse', 'Mouse sem fio')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet res = stm.getGeneratedKeys();
		while (res.next()) {
			Integer id = res.getInt(1);
			System.out.println("O id gerado é: " + id);
		}

		con.close();
		stm.close();
		res.close();
	}
}
