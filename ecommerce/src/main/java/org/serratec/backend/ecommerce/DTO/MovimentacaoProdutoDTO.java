package org.serratec.backend.ecommerce.DTO;

public class MovimentacaoProdutoDTO {
	private Integer quantidadeCompra;
	private Double valorUnitario;
	private Integer idProduto;
	
	
	public MovimentacaoProdutoDTO() {
	
	}


	public Integer getQuantidadeCompra() {
		return quantidadeCompra;
	}


	public void setQuantidadeCompra(Integer quantidadeCompra) {
		this.quantidadeCompra = quantidadeCompra;
	}


	public Double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public Integer getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
}
