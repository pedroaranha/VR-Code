package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="compra")
public class Compra implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compra")
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_produto", nullable=false)
	private Produto produto;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_funcionario", nullable=false)
	private Funcionario funcionario = new Funcionario();
	
	@Column(name="quantidade", nullable=false)
	private int quantidade;
	
	@Column(name="data_compra", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCompra;
	
	@Column(name="valor_compra", nullable=false)
	private Double valorTotalCompra;

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Double getValorTotalCompra() {
		return valorTotalCompra;
	}

	public void setValorTotalCompra(Double valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}
	
	
	
}
