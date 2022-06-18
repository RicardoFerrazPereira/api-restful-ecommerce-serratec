package org.serratec.backend.ecommerce.DTO;

public class UsuarioDTO {
	
	private Integer idUsuario;
	private String username;
	private String password;
	
	public UsuarioDTO() {}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
