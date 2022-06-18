package org.serratec.backend.ecommerce.DTO;

import java.time.LocalDate;
import java.util.Date;

public class ProdutoDTO {
	
	
	private Integer idProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private Double valorUnitario;
	private LocalDate dataValidade;
	private Integer quantidadeEmEstoque;
	private String peridoDeGarantia;
	private Integer idCategoria;
	private Integer idFuncionario;
	
	
	public ProdutoDTO() {}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}


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

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
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

	

}
	