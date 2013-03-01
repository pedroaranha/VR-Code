package domain;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import enumerations.TipoUsuario;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, unique=true)
	@Length(min=6, message="o login deve conter no minimo 6 caracteres")
	@NotEmpty(message="O campo login não pode ser vazio!")
	private String login;

	@Column(nullable = false)
	@Length(min=6, max=6, message="A senha deve conter 6 caracteres.")
	@NotEmpty(message="O campo senha não pode ser vazio!")
	private String senha;

	@Column(name = "tipo_usuario",  nullable=true)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	public Usuario() {

	}

	public Usuario(String nome, String login, String senha, TipoUsuario tipo) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public static Usuario getUsuarioFromSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return usuario;
	}

	public static void setUsuarioOnSession(Usuario usuario) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.setAttribute("usuario", usuario);
	}
	
	public static void setIdUsuarioOnSession(Usuario usuario) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.setAttribute("idUsuario", usuario.getId());
	}
	
	public static Long getIdUsuarioOnSession(Usuario usuario) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		return idUsuario;
	}
}
