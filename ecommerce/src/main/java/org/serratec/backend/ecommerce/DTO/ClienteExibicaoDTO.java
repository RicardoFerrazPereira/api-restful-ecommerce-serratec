package org.serratec.backend.ecommerce.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.serratec.backend.ecommerce.model.Endereco;
import org.serratec.backend.ecommerce.model.Movimentacao;



public class ClienteExibicaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idCliente;
	private String nomeCliente;
	private String clienteUsuario;
	private String emailCliente;
	private String cpfCliente;
	private LocalDate dataNascimento;
	private String telefoneCliente;
	private String telefoneSec;
	private List<Movimentacao> listaPedidos;
	private List<Endereco> listaEndereco;
	
	// Constructor
	public ClienteExibicaoDTO() {}

	// Getters and Setters
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getTelefoneSec() {
		return telefoneSec;
	}

	public void setTelefoneSec(String telefoneSec) {
		this.telefoneSec = telefoneSec;
	}

	public List<Movimentacao> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Movimentacao> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<Endereco> getListaEndereco() {
		return listaEndereco;
	}

	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}

	
}
