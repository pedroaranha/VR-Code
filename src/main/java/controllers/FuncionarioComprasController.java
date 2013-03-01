package controllers;

import interfaces.FuncionarioDAO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import persistencia.CompraDB;
import persistencia.FuncionarioDB;
import domain.Compra;
import domain.Funcionario;
import domain.Usuario;

@ManagedBean(name = "funcionarioComprasController")
public class FuncionarioComprasController implements Serializable {

	private static final long serialVersionUID = 1L;

	public String idfuncionario = "";
	private CompraDB compraDB = new CompraDB();
	private boolean exibirForm = false;
	private List<SelectItem> funcionarios;
	@SuppressWarnings("rawtypes")
	private ListDataModel funcionarioCompras;
	private Double valorTotalExtrato = 0.0;
	private FuncionarioDB funcionarioDB = null;
 
	{
		carregarFuncionarios();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void listarFuncionarioCompras() {
		this.setFuncionarioCompras(new ListDataModel(compraDB.listarComprasPorFuncionario(idfuncionario)));
		this.ocultarForm();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListDataModel getExtratoCompras() {
		funcionarioDB = new FuncionarioDB();
		Funcionario funcionario = funcionarioDB.procurarFuncionarioByUsuario(Usuario.getUsuarioFromSession().getId());

		return new ListDataModel(compraDB.listarComprasPorFuncionario(funcionario));
	}
	
	public void exibirForm() {
		exibirForm = true;
	}

	public String alterarCompra() {
		this.exibirForm();
		return null;
	}

	public void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));
	}

	public void ocultarForm() {
		exibirForm = false;
	}

	public String cancelarCadastro() {
		this.ocultarForm();
		return null;
	}

	public boolean isExibirForm() {
		return exibirForm;
	}

	public void carregarFuncionarios() {
		FuncionarioDAO db = new FuncionarioDB();
		List<Funcionario> lista = db.listarFuncionarios();

		if (lista != null && !lista.isEmpty()) {
			this.funcionarios = new ArrayList<SelectItem>();

			for (Funcionario funcionario : lista) {
				funcionario.setCodigo(funcionario.getId().toString());
				this.funcionarios.add(new SelectItem(funcionario.getCodigo(), funcionario
						.getNome()));
			}
		}
	}

	public List<SelectItem> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<SelectItem> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@SuppressWarnings("rawtypes")
	public ListDataModel getFuncionarioCompras() {
		return funcionarioCompras;
	}

	@SuppressWarnings("rawtypes")
	public void setFuncionarioCompras(ListDataModel funcionarioCompras) {
		this.funcionarioCompras = funcionarioCompras;
	}

	public String getIdfuncionario() {
		return idfuncionario;
	}

	public void setIdfuncionario(String idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public Double getValorTotalExtrato() {
		funcionarioDB = new FuncionarioDB();
		Funcionario funcionario = funcionarioDB.procurarFuncionarioByUsuario(Usuario.getUsuarioFromSession().getId());
		List<Compra> compras = compraDB.listarComprasPorFuncionario(funcionario);
		
		if(compras != null){
			valorTotalExtrato = 0.0;
			for(Compra c : compras){
				valorTotalExtrato += c.getValorTotalCompra();
			}
		
			DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
			symbols.setDecimalSeparator('.');
			DecimalFormat format = new DecimalFormat("#.##", symbols);
			valorTotalExtrato = Double.valueOf(format.format(valorTotalExtrato));
		}
		return valorTotalExtrato;
	}

	public void setValorTotalExtrato(Double valorTotalExtrato) {
		this.valorTotalExtrato = valorTotalExtrato;
	}
	
}
