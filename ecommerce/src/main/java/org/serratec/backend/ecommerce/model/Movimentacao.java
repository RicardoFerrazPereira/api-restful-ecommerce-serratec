package org.serratec.backend.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="movimentacao")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movimentacao_cd_id")
	private Integer idMovimentacao;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", referencedColumnName="cliente_cd_id")
	@JsonIgnore
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="produto_id", referencedColumnName="produto_cd_id")
	@JsonIgnore
	private Produto produto;
	
	@Column(name="movimentacao_valor_unitario")
	@NotNull
	private Double valorUnitario;
	
	@Column(name="movimentacao_nota_fiscal")
	@NotNull
	private String notaFiscal;
	
	@Column(name="movimentacao_quantidade_compra")
	@NotNull
	private Integer quantidadeCompra;
	
	@Column(name="movimentacao_tipo")
	@NotNull
	private String tipoMovimentacao;
	
		
	public Movimentacao() {
		
	}


	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}


	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public String getNotaFiscal() {
		return notaFiscal;
	}


	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}


	public Integer getQuantidadeCompra() {
		return quantidadeCompra;
	}


	public void setQuantidadeCompra(Integer quantidadeCompra) {
		this.quantidadeCompra = quantidadeCompra;
	}


	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}


	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
}
