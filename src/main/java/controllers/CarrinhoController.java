package controllers;

import interfaces.ProdutoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import persistencia.CompraDB;
import persistencia.FuncionarioDB;
import persistencia.ProdutoDB;
import domain.Carrinho;
import domain.Compra;
import domain.Funcionario;
import domain.Item;
import domain.Produto;
import domain.Usuario;

@ManagedBean(name = "carrinhoController")
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Carrinho carrinho;

	{
		// Carrinho.setCarrinhoOnSession(new Carrinho());
		this.carrinho = Carrinho.getCarrinhoFromSession();
	}

	private Item produtoSelecionado;
	private Long itemId;

	public Funcionario getFuncionario() {
		Usuario user = Usuario.getUsuarioFromSession();
		FuncionarioDB f = new FuncionarioDB();
		Funcionario funcionario = f.procurarFuncionarioByUsuario(user.getId());

		if (funcionario == null) {
			funcionario = new Funcionario();
		}

		return funcionario;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListDataModel getItensProduto() {
		List<Item> itens = new ArrayList<Item>();
		ProdutoDAO produtoDB = new ProdutoDB();
		List<Produto> produtos = produtoDB.listarProdutos();

		if (produtos != null) {
			/*
			 * Pega parâmetro do HTTP GET Request "produto_id" e converte-o para
			 * Long, se não for nulo. Se for nulo, lista todos os produtos
			 * normalmente.
			 */
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String produto_id_string = request.getParameter("produto_id");
			if (produto_id_string != null) {
				Long produto_id = Long.parseLong(produto_id_string, 10);
				for (Produto p : produtos) {
					if (p.getId() == produto_id) {
						Item item = new Item();
						item.setProduto(p);
						itens.add(item);
					}
				}
			} else {
				for (Produto p : produtos) {
					Item item = new Item();
					item.setProduto(p);
					itens.add(item);
				}
			}
		}

		return new ListDataModel(itens);
	}

	public void comprar() {
		if (produtoSelecionado != null) {
			this.adiciona(produtoSelecionado);
			Carrinho.atualizarCarrinhoOnSession(carrinho);
			this.mostrarMensagem("Produto adicionado ao seu carrinho.");
		} else {
			this.mostrarMensagem("O Produto não foi adicionado ao seu carrinho.");
		}
	}

	public Item getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Item produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void adiciona(Item item) {
		carrinho.adiciona(item);
	}

	public void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));
	}

	public void excluir() {
		carrinho.remove(itemId);
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Double getValorCompra(){
		Double valor = 0.0;
		
		if(carrinho != null){
			for (Item item : carrinho.getItens()) {
				valor += item.getQuantidade() * item.getProduto().getPreco();
			}	
		}
		
		return valor;
	}
	
	
	public void finalizarCompra() {
		if (carrinho != null) {
			FuncionarioDB f = new FuncionarioDB();

			Funcionario funcionario = this.getFuncionario();
			CompraDB c = new CompraDB();

			for (Item item : carrinho.getItens()) {

				Compra compra = new Compra();
				compra.setFuncionario(funcionario);
				compra.setDataCompra(new Date());
				compra.setProduto(item.getProduto());
				compra.setQuantidade(item.getQuantidade());
				compra.setValorTotalCompra(item.getQuantidade() * item.getProduto().getPreco());

				if (funcionario.getSaldo() >= compra.getValorTotalCompra()) {
					if (c.inserirCompra(compra)) {
						funcionario.setSaldo(funcionario.getSaldo() - compra.getValorTotalCompra());
						f.alterarFuncionario(funcionario);
						carrinho = Carrinho.limparCarrinho();
						Carrinho.atualizarCarrinhoOnSession(carrinho);
					}
				}

			}

		}
	}

}
