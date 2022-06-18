package org.serratec.backend.ecommerce.DTO;

import java.io.Serializable;

public class FuncionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idFuncionario;
	private String nomeFuncionario;
	private String cpfFuncionario;

	public FuncionarioDTO() {
	}

	public FuncionarioDTO(Integer idFuncionario, String nomeFuncionario, String cpfFuncionario) {
		super();
		this.idFuncionario = idFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.cpfFuncionario = cpfFuncionario;
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

}
