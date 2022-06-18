package org.serratec.backend.ecommerce.repository;

import org.serratec.backend.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
