package exemplojsp;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;

import br.edu.ifpb.pweb1.model.dao.ProdutosDAO;
import br.edu.ifpb.pweb1.model.domain.Produto;

public class ProdutosTeste {

	@Test
	public void criarProduto() {
		ProdutosDAO produtosDAO = new ProdutosDAO();
		Produto produto = new Produto();
		produto.setEstoque(10l);
		produto.setNome("Fantasia");
		produto.setPreco(50.00);
		produto.setDescricao("Importante para o carnaval");
		produto.setDataCadastro(Timestamp.from(Instant.now()));
		produtosDAO.insert(produto);
		assertNotNull(produto.getId());
	}
	
}
