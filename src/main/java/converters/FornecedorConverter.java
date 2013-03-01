package converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import persistencia.FornecedorDB;
import domain.Fornecedor;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		Fornecedor fornecedor = null;

		if (value != null) {
			FornecedorDB bd = new FornecedorDB();
			fornecedor = bd.procurarFornecedor(value);
		}

		return fornecedor;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value.equals("") || value == null) {
			return null;
		}

		return ((Fornecedor) value).getId().toString();

	}

}
