package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Fornecedor(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fornecedor")
	private Long id;
	
	@Transient
	private String codigo;

	@Column(nullable = false)
	private String nome;

	@OneToMany(mappedBy = "fornecedor", targetEntity = Produto.class, fetch = FetchType.LAZY, orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", referencedColumnName="id_usuario", nullable=false)
	private Usuario usuario = new Usuario();

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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
    public boolean equals(Object fornecedor) {
        return fornecedor instanceof Fornecedor && (id != null) ? id.equals(((Fornecedor) fornecedor).id) : (fornecedor == this);
    }

    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    public String toString() {
        return "Fornecedor[" + id + "," + nome + "]";
    }

}
