package controllers;

import interfaces.FuncionarioDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import persistencia.EmpresaDB;
import persistencia.FuncionarioDB;
import domain.Empresa;
import domain.Funcionario;
import domain.Usuario;
import enumerations.TipoUsuario;

@ManagedBean
public class FuncionarioController {

	private FuncionarioDAO funcionarioDB = new FuncionarioDB();
	private Funcionario funcionario;
	private boolean exibirForm = false;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListDataModel getFuncionarios() {
		return new ListDataModel(funcionarioDB.listarFuncionarios());
	}

	public String adicionarFuncionario() {
		this.limparFuncionario();
		this.exibirForm();
		return null;
	}

	public void exibirForm() {
		exibirForm = true;
	}

	public void limparFuncionario() {
		funcionario = new Funcionario();
	}

	public String alterarFuncionario() {
		this.exibirForm();
		return null;
	}

	public String excluirFuncionario() {
		funcionarioDB.excluirFuncionario(funcionario);
		this.mostrarMensagem(funcionario.getNome() + " foi excluido!");
		return null;
	}

	public String salvarFuncionario() {

		if (funcionario != null) {
			if (funcionario.getId() == null || funcionario.getId() == 0) {
				this.completarDadosUsuario();
				if (funcionarioDB.inserirFuncionario(funcionario)) {
					this.mostrarMensagem(funcionario.getNome() + " salvo com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(funcionario.getNome() + " não salvo!");
					this.ocultarForm();
				}
			} else {
				this.completarDadosUsuario();
				if (funcionarioDB.alterarFuncionario(funcionario)) {
					this.mostrarMensagem(funcionario.getNome() + " alterado com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(funcionario.getNome() + " não alterado!");
					this.ocultarForm();
				}
			}

		}

		return null;
	}

	public void ocultarForm() {
		exibirForm = false;
	}

	public void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));
	}

	public String cancelarCadastro() {
		this.ocultarForm();
		return null;
	}

	public boolean isExibirForm() {
		return exibirForm;
	}

	public void completarDadosUsuario() {
		funcionario.getUsuario().setNome(funcionario.getNome());
		funcionario.getUsuario().setTipoUsuario(TipoUsuario.FUNCIONARIO);
		
		EmpresaDB e = new EmpresaDB();
		Empresa empresa = e.procurarEmpresaByUsuario(Usuario.getUsuarioFromSession().getId());
		
		funcionario.setEmpresa(empresa);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
