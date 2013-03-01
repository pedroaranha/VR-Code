package domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class Carrinho {

	private static Carrinho carrinho;

	private Carrinho() {

	}

	public static Carrinho getInstance() {
		if (Carrinho.carrinho == null) {
			carrinho = new Carrinho();
		}
		return carrinho;
	}

	public static Carrinho limparCarrinho() {
		if (Carrinho.carrinho != null) {
			carrinho = new Carrinho();
		}
		return carrinho;
	}

	private List<Item> itensProduto = new ArrayList<Item>();

	private Double total = 0.0;

	public List<Item> getItens() {
		return itensProduto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListDataModel getItensCarrinho() {
		return new ListDataModel(itensProduto);
	}

	public void setItens(List<Item> itens) {
		this.itensProduto = itens;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void adiciona(Item item) {
		itensProduto.add(item);
		total += item.getProduto().getPreco() * item.getQuantidade();
	}

	public Integer getTotalDeItens() {
		return itensProduto.size();
	}

	public void remove(int indiceItem) {
		Item removido = itensProduto.remove(indiceItem);
		total -= removido.getProduto().getPreco() * removido.getQuantidade();
	}

	public boolean remove(Long itemId) {
		boolean retorno = false;
		Item item = null;

		for (Item i : itensProduto) {
			if (i.getProduto().getId() == itemId) {
				item = i;
				break;
			}
		}

		if (item != null && item.getProduto().getId() == itemId) {
			retorno = itensProduto.remove(item);
			if (retorno) {
				total -= item.getProduto().getPreco() * item.getQuantidade();
			}
		}

		return retorno;
	}

	public static Carrinho getCarrinhoFromSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		return carrinho;
	}

	public static void setCarrinhoOnSession(Carrinho carrinho) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.setAttribute("carrinho", carrinho);
	}

	public static void atualizarCarrinhoOnSession(Carrinho carrinho) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.removeAttribute("carrinho");
		session.setAttribute("carrinho", carrinho);
	}

}
