package interfaces;

import java.util.List;

import domain.Funcionario;

public interface FuncionarioDAO {

	boolean inserirFuncionario(Funcionario funcionario);

	boolean alterarFuncionario(Funcionario funcionario);

	boolean excluirFuncionario(Funcionario funcionario);

	Funcionario procurarFuncionario(Funcionario funcionario);

	List<Funcionario> listarFuncionarios();

}
