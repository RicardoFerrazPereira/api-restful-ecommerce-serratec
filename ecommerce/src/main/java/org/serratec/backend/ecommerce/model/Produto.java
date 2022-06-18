package org.serratec.backend.ecommerce.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="produto")
public class Produto implements Serializable{

	private static final long  serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="produto_cd_id")
	private Integer idProduto;
	
	@Column(name="produto_tx_nome")
	@NotNull
	private String nomeProduto;
	
	@Column(name="produto_tx_descricao")
	@NotNull
	private String descricaoProduto;
	
	@Column(name="produto_valor_unitario")
	@NotNull
	private Double valorUnitario;
	
	@Column(name="produto_dt_data_validade")
	@NotNull
	private LocalDate dataValidadeProduto;
	
	@Column(name="produto_tx_quantidade_em_estoque")
	@NotNull
	private Integer quantidadeEmEstoque;
	
	@Column(name="produto_tx_periodo_de_garantia")
	@NotNull
	private String peridoDeGarantia;
	
				
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "categoria_cd_id")
	@JsonIgnore
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_cd_id")
	@JsonIgnore
	private Funcionario funcionario;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Movimentacao> listaVendas;
	
	
	public Categoria getCategoria() {
	return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Produto() {}

	
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	
	
	public Double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public LocalDate getDataValidadeProduto() {
		return dataValidadeProduto;
	}


	public void setDataValidadeProduto(LocalDate dataValidadeProduto) {
		this.dataValidadeProduto = dataValidadeProduto;
	}


	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}


	public String getPeridoDeGarantia() {
		return peridoDeGarantia;
	}


	public void setPeridoDeGarantia(String peridoDeGarantia) {
		this.peridoDeGarantia = peridoDeGarantia;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public List<Movimentacao> getListaVendas() {
		return listaVendas;
	}


	public void setListaVendas(List<Movimentacao> listaVendas) {
		this.listaVendas = listaVendas;
	}
	
	
		
}
