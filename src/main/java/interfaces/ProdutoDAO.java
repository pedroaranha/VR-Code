package interfaces;

import java.util.List;

import domain.Produto;

public interface ProdutoDAO {

	boolean inserirProduto(Produto produto);

	boolean alterarProduto(Produto produto);

	boolean excluirProduto(Produto produto);

	Produto procurarProduto(Produto produto);

	List<Produto> listarProdutos();
}
