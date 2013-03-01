package interfaces;

import java.util.List;

import domain.Compra;
import domain.Funcionario;

public interface CompraDAO {
	
	boolean inserirCompra(Compra compra);
	
	boolean alterarCompra(Compra compra);
	
	boolean excluirCompra(Compra compra);

	Compra procurarCompra(Compra compra);
	
	List<Compra> listarCompras();
	
	List<Compra> listarComprasPorFuncionario(Funcionario funcionario);
}
