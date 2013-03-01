package converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import persistencia.FuncionarioDB;
import domain.Funcionario;

@FacesConverter(value = "funcionarioConverter")
public class FuncionarioConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		Funcionario funcionario = null;

		if (value != null) {
			FuncionarioDB bd = new FuncionarioDB();
			funcionario = bd.procurarFuncionario(value);
		}

		return funcionario;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value.equals("") || value == null) {
			return null;
		}

		return ((Funcionario) value).getId().toString();

	}
	

}
