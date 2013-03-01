package controllers;

import interfaces.FornecedorDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import persistencia.FornecedorDB;
import domain.Fornecedor;
import enumerations.TipoUsuario;

@ManagedBean
public class FornecedorController {

	private FornecedorDAO fornecedorDB = new FornecedorDB();
	private Fornecedor fornecedor;
	private boolean exibirForm = false;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListDataModel getFornecedores() {
		return new ListDataModel(fornecedorDB.listarFornecedores());
	}

	public String adicionarFornecedor() {
		this.limparFornecedor();
		this.exibirForm();
		return null;
	}

	public void exibirForm() {
		exibirForm = true;
	}

	public void limparFornecedor() {
		fornecedor = new Fornecedor();
	}

	public String alterarFornecedor() {
		this.exibirForm();
		return null;
	}

	public String excluirFornecedor() {
		fornecedorDB.excluirFornecedor(fornecedor);
		this.mostrarMensagem(fornecedor.getNome() + " foi excluido!");
		return null;
	}

	public String salvarFornecedor() {

		if (fornecedor != null) {
			if (fornecedor.getId() == null || fornecedor.getId() == 0) {
				this.completarDadosUsuario();
				if (fornecedorDB.inserirFornecedor(fornecedor)) {
					this.mostrarMensagem(fornecedor.getNome() + " salvo com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(fornecedor.getNome() + " não salvo!");
					this.ocultarForm();
				}
			} else {
				this.completarDadosUsuario();
				if (fornecedorDB.alterarFornecedor(fornecedor)) {
					this.mostrarMensagem(fornecedor.getNome() + " alterado com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(fornecedor.getNome() + " não alterado!");
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
		fornecedor.getUsuario().setNome(fornecedor.getNome());
		fornecedor.getUsuario().setTipoUsuario(TipoUsuario.FORNECEDOR);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
