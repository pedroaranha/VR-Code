package controllers;

import interfaces.EmpresaDAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import persistencia.EmpresaDB;
import domain.Empresa;
import enumerations.TipoUsuario;

@ManagedBean
public class EmpresaController {

	private EmpresaDAO empresaDB = new EmpresaDB();
	private Empresa empresa;
	private boolean exibirForm = false;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListDataModel getEmpresas() {
		return new ListDataModel(empresaDB.listarEmpresas());
	}

	public String adicionarEmpresa() {
		this.limparEmpresa();
		this.exibirForm();
		return null;
	}

	public void exibirForm() {
		exibirForm = true;
	}

	public void limparEmpresa() {
		empresa = new Empresa();
	}

	public String alterarEmpresa() {
		this.exibirForm();
		return null;
	}

	public String excluirEmpresa() {
		empresaDB.excluirEmpresa(empresa);
		this.mostrarMensagem(empresa.getNome() + " foi excluida!");
		return null;
	}

	public String salvarEmpresa() {

		if (empresa != null) {
			if (empresa.getId() == null || empresa.getId() == 0) {
				this.completarDadosUsuario();
				if (empresaDB.inserirEmpresa(empresa)) {
					this.mostrarMensagem(empresa.getNome() + " salva com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(empresa.getNome() + " não salva!");
					this.ocultarForm();
				}
			} else {
				this.completarDadosUsuario();
				if (empresaDB.alterarEmpresa(empresa)) {
					this.mostrarMensagem(empresa.getNome() + " alterada com sucesso!");
					this.ocultarForm();
				} else {
					this.mostrarMensagem(empresa.getNome() + " não alterada!");
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isExibirForm() {
		return exibirForm;
	}

	public void completarDadosUsuario() {
		empresa.getUsuario().setNome(empresa.getNome());
		empresa.getUsuario().setTipoUsuario(TipoUsuario.EMPRESA);
	}

}
