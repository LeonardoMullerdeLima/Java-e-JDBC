import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.loja.jdbc.ConnectionFactory;
import br.com.loja.jdbc.dao.CategoriaDAO;
import br.com.loja.jdbc.dao.ProdutoDAO;
import br.com.loja.jdbc.modelo.Categoria;
import br.com.loja.jdbc.modelo.Produto;

public class TestaCategoriaDAO {

	public static void main(String[] args) throws SQLException {

		try (Connection con = new ConnectionFactory().recuperaConecxao()) {
			CategoriaDAO categoriaDao = new CategoriaDAO(con);
			List<Categoria> listaDeCategorias = categoriaDao.listarComProdutos();
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct);

				for (Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getNome());
				}
			});
		}
	}
}
