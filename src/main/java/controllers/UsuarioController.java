/**
 * 
 */
package controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import domain.Carrinho;
import domain.Usuario;
import persistencia.UsuarioDB;
import interfaces.UsuarioDAO;

/**
 * @author davi.aguiar
 * 
 */
@ManagedBean
public class UsuarioController {

	private UsuarioDAO usuarioDB = new UsuarioDB();
	private Usuario usuario;
	private boolean exibirForm = false;
	private String login;
	private String senha;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListDataModel getUsuarios() {
		return new ListDataModel(usuarioDB.listarUsuarios());
	}

	public String adicionarUsuario() {
		this.limparUsuario();
		this.exibirForm();
		return null;
	}

	public String alterarUsuario() {
		this.exibirForm();
		return null;
	}

	public String salvarUsuario() {
		if (usuario != null) {

			if (usuario.getId() == null || usuario.getId() == 0) {
				usuarioDB.inserirUsuario(usuario);
			} else {
				usuarioDB.alterarUsuario(usuario);
			}
			this.mostrarMensagem(usuario.getNome() + " foi salvo com sucesso!");
			this.ocultarForm();
		}
		return null;
	}

	public String excluirUsuario() {
		usuarioDB.excluirUsuario(usuario);
		this.mostrarMensagem(usuario.getNome() + " foi excluido!");
		return null;
	}

	private void mostrarMensagem(String string) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(string));

	}

	public String cancelarCadastroUsuario() {
		this.ocultarForm();
		return null;
	}

	private void limparUsuario() {
		usuario = new Usuario();
	}

	private void exibirForm() {
		exibirForm = true;
	}

	private void ocultarForm() {
		exibirForm = false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isExibirForm() {
		return exibirForm;
	}

	public String realizarLogin() {

		String tipoUsuario = "";

		if (this.validarLogin() && this.usuario != null) {
			
			Carrinho.setCarrinhoOnSession(Carrinho.getInstance());
			Usuario.setUsuarioOnSession(usuario);
			Usuario.setIdUsuarioOnSession(usuario);

			if(usuario.getTipoUsuario() == null){
				tipoUsuario = "erro";
			}else{
			
				if (usuario.getTipoUsuario().toString().equalsIgnoreCase("ADMINISTRADOR")
						|| usuario.getTipoUsuario().toString().equalsIgnoreCase("EMPRESA")
						|| usuario.getTipoUsuario().toString().equalsIgnoreCase("FUNCIONARIO")
						|| usuario.getTipoUsuario().toString().equalsIgnoreCase("FORNECEDOR")) {
					tipoUsuario = usuario.getTipoUsuario().toString();
				} else  {
					tipoUsuario = "erro";
				}
			}
		}
		return tipoUsuario;
	}

	public boolean validarLogin() {
		boolean valido = false;
		UsuarioDB db = new UsuarioDB();
		List<Usuario> lista = db.listarUsuarios();

		if (lista != null) {
			for (Usuario user : lista) {
				if (this.getLogin().equals(user.getLogin())
						&& this.getSenha().equals(user.getSenha())) {
					valido = true;
					this.usuario = user;
				}
			}
		}
		return valido;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
