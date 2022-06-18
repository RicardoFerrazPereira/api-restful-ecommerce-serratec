package org.serratec.backend.ecommerce.DTO;

import java.util.List;

import org.serratec.backend.ecommerce.model.Produto;



public class CategoriaExibicaoDTO {

	private Integer idCategoria;
	private String nomeCategoria;
	private String descricaoCategoria;
	private List<Produto> listaProduto;
	
	
	
	public CategoriaExibicaoDTO() {}
	
	
	public List<Produto> getListaProduto() {
	return listaProduto;

	}
	public void setListaProduto(List<Produto> listaProduto) {
	this.listaProduto = listaProduto;
	}
	
	public Integer getIdCategoria() {
	return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
}
