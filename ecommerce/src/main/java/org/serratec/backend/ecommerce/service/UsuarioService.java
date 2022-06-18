package org.serratec.backend.ecommerce.service;

import org.serratec.backend.ecommerce.DTO.UsuarioDTO;
import org.serratec.backend.ecommerce.model.Usuario;
import org.serratec.backend.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private UsuarioDTO mapToDTO(Usuario usuario, UsuarioDTO udto) {
		udto.setUsername(usuario.getUsername());
		udto.setPassword(usuario.getPassword());
		udto.setIdUsuario(usuario.getIdUsuario());
		return udto;
	}
	
	private Usuario mapToModel(Usuario usuario, UsuarioDTO udto) {
		usuario.setUsername(udto.getUsername());
		usuario.setPassword(encoder.encode(udto.getPassword()));
		return usuario;
	}
	
	public UsuarioDTO buscarPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
//	public List<UsuarioDTO> buscarTodos() {
//		return usuarioRepository.findAll()
//				.stream()
//				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
//				.collect(Collectors.toList());
//	}
	
	public UsuarioDTO buscarPorUsername(String username) {
		return usuarioRepository.findAll()
				.stream()
				.filter(usuario ->  usuario.getUsername().equals(username))
				.map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public Integer salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		mapToModel(usuario, usuarioDTO);
		usuarioRepository.save(usuario);
		return usuario.getIdUsuario();
	}

}
