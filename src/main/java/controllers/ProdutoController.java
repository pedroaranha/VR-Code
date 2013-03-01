package controllers;

import interfaces.FornecedorDAO;
import interfaces.ProdutoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import persistencia.FornecedorDB;
import persistencia.ProdutoDB;
import domain.Fornecedor;
import domain.Produto;

@ManagedBean(name = "produtoController")
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProdutoDAO produtoDB = new ProdutoDB();
	private Produto produto;
	private boolean exibirForm = false;
	private List<SelectItem> selectItems;

	{
		carregarFornecedores();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListDataModel getProdutos() {
		return new ListDataModel(produtoDB.listarProdutos());
	}

	public String adicionarProduto() {
		this.limparProduto();
		this.exibirForm();
		return null;
	}

	public void exibirForm() {
		exibirForm = true;
	}

	public void limparProduto() {
		produto = new Produto();
	}

	public String alterarProduto() {
		this.exibirForm();
		return null;
	}

	public String excluirProduto() {
		produtoDB.excluirProduto(produto);
		this.mostrarMensagem(produto.getNome() + " foi excluído!");
		return null;
	}

	public void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));
	}

	public String salvarProduto() {

		if (produto != null) {
			if (produto.getId() == null || produto.getId() == 0) {
				produtoDB.inserirProduto(produto);
				this.mostrarMensagem(produto.getNome() + " salvo com sucesso!");
			} else {
				produtoDB.alterarProduto(produto);
				this.mostrarMensagem(produto.getNome() + " alterado com sucesso!");
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<SelectItem> getFornecedores() {
		return this.selectItems;
	}

	public void carregarFornecedores() {
		FornecedorDAO db = new FornecedorDB();
		List<Fornecedor> lista = db.listarFornecedores();
		this.selectItems = new ArrayList<SelectItem>();

		if (lista != null && !lista.isEmpty()) {

			for (Fornecedor fornecedor : lista) {
				fornecedor.setCodigo(fornecedor.getId().toString());
				this.selectItems.add(new SelectItem(fornecedor, fornecedor
						.getNome()));
			}
		}
	}

}
