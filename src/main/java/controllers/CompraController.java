package controllers;

import interfaces.CompraDAO;
import interfaces.ProdutoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import persistencia.CompraDB;
import persistencia.FuncionarioDB;
import persistencia.ProdutoDB;
import domain.Compra;
import domain.Funcionario;
import domain.Produto;

@ManagedBean(name = "compraController")
public class CompraController implements Serializable {

	private static final long serialVersionUID = 1L;

	private CompraDAO compraDB = new CompraDB();
	private Compra compra;
	private boolean exibirForm = false;
	private List<SelectItem> selectItems;

	{
		carregarProdutos();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListDataModel getCompras() {
		return new ListDataModel(compraDB.listarCompras());
	}

	public String adicionarCompra() {
		this.limparCompra();
		this.exibirForm();
		return null;
	}

	public void exibirForm() {
		exibirForm = true;
	}

	public void limparCompra() {
		compra = new Compra();
	}

	public String alterarCompra() {
		this.exibirForm();
		return null;
	}

	public String excluirCompra() {
		compraDB.excluirCompra(compra);
		this.mostrarMensagem("compra: " + compra.getId() + " foi excluída!");
		return null;
	}

	public void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));
	}

	public String salvarCompra() {

		if (compra != null) {
			if (compra.getId() == null || compra.getId() == 0) {
				this.completarDadosCompra();
				if (compraDB.inserirCompra(compra)) {
					FuncionarioDB bd = new FuncionarioDB();
					bd.alterarFuncionario(compra.getFuncionario());
					this.mostrarMensagem("compra " + compra.getId()
							+ " : salva com sucesso!");
				}
			} else {
				this.completarDadosCompra();
				if (compraDB.alterarCompra(compra)) {
					FuncionarioDB bd = new FuncionarioDB();
					bd.alterarFuncionario(compra.getFuncionario());
					this.mostrarMensagem("compra " + compra.getId()
							+ " : alterada!");
				}
			}
			
			this.ocultarForm();
		}

		return null;
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void completarDadosCompra() {
		FuncionarioDB bd = new FuncionarioDB();
		Funcionario funcionario = bd.procurarFuncionario(compra
				.getFuncionario().getCodigo());

		if (funcionario != null) {
			compra.setValorTotalCompra(compra.getQuantidade()
					* compra.getProduto().getPreco());
			funcionario.setSaldo(funcionario.getSaldo()
					- compra.getValorTotalCompra());
			compra.setFuncionario(funcionario);
			compra.setDataCompra(new Date());
		}
	}

	public List<SelectItem> getProdutos() {
		return this.selectItems;
	}

	public void carregarProdutos() {
		ProdutoDAO db = new ProdutoDB();
		List<Produto> lista = db.listarProdutos();

		if (lista != null && !lista.isEmpty()) {
			this.selectItems = new ArrayList<SelectItem>();

			for (Produto produto : lista) {
				this.selectItems
						.add(new SelectItem(produto, produto.getNome()));
			}
		}
	}

}
