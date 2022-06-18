package org.serratec.backend.ecommerce.repository;

import java.util.Optional;

import org.serratec.backend.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query(value="FROM Usuario u WHERE u.username = ?1")
	Optional<Usuario> buscarPorLogin(String username);

}
