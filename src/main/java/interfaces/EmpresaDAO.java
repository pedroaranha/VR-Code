package interfaces;

import java.util.List;

import domain.Empresa;

public interface EmpresaDAO {

	boolean inserirEmpresa(Empresa empresa);

	boolean alterarEmpresa(Empresa empresa);

	boolean excluirEmpresa(Empresa empresa);

	Empresa procurarEmpresa(Empresa empresa);

	List<Empresa> listarEmpresas();

}
