package org.serratec.backend.ecommerce.DTO;

import java.util.List;


public class MovimentacaoExibicaoDTO {
	
	private Integer idMovimentacao;
	private Integer idCliente;
	private String notaFiscal;
	private String tipoMovimentacao;
	private List<MovimentacaoProdutoDTO> listaProdutos;
	
		
	public MovimentacaoExibicaoDTO() {
		
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



	public List<MovimentacaoProdutoDTO> getListaProdutos() {
		return listaProdutos;
	}



	public void setListaProdutos(List<MovimentacaoProdutoDTO> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}


	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}


	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}
}
