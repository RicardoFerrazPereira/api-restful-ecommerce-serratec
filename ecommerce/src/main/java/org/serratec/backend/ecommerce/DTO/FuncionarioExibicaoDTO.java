package org.serratec.backend.ecommerce.DTO;

import java.io.Serializable;
import java.util.List;

import org.serratec.backend.ecommerce.model.Produto;

public class FuncionarioExibicaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idFuncionario;
	private String nomeFuncionario; 
	private String cpfFuncionario;
	private List<Produto> listaDeProdutos; 

	public FuncionarioExibicaoDTO() {
	}

	public FuncionarioExibicaoDTO(Integer idFuncionario, String nomeFuncionario, String cpfFuncionario,
			List<Produto> listaDeProdutos) {
		super();
		this.idFuncionario = idFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.cpfFuncionario = cpfFuncionario;
		this.listaDeProdutos = listaDeProdutos;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

}
