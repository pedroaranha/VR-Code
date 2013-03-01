package interfaces;

import java.util.List;

import domain.Fornecedor;

public interface FornecedorDAO {

	boolean inserirFornecedor(Fornecedor fornecedor);
	
	boolean alterarFornecedor(Fornecedor fornecedor);
	
	boolean excluirFornecedor(Fornecedor fornecedor);
	
	Fornecedor procurarFornecedor(Fornecedor fornecedor);
	
	List<Fornecedor> listarFornecedores();
	
}
