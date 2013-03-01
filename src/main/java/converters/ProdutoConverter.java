package converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import persistencia.ProdutoDB;
import domain.Produto;

@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		Produto produto = null;

		if (value != null) {
			ProdutoDB bd = new ProdutoDB();
			produto = bd.procurarProduto(value);
		}

		return produto;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value.equals("") || value == null) {
			return null;
		}

		return ((Produto) value).getId().toString();

	}

}
