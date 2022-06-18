package org.serratec.backend.ecommerce.DTO;

import java.util.List;

public class MovimentacaoDTO {
	
	private Integer idMovimentacao;
	private Integer idCliente;
	private String notaFiscal;
	private String tipoMovimentacao;	
	private List<MovimentacaoProdutoDTO> listaProduto;
	

	public MovimentacaoDTO() {
		
	}



	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}



	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}



	public Integer getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public String getNotaFiscal() {
		return notaFiscal;
	}



	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}



	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}



	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}



	public List<MovimentacaoProdutoDTO> getListaProduto() {
		return listaProduto;
	}



	public void setListaProduto(List<MovimentacaoProdutoDTO> listaProduto) {
		this.listaProduto = listaProduto;
	}
}
