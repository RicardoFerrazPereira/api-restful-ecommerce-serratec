package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.CategoriaDTO;
import org.serratec.backend.ecommerce.DTO.CategoriaExibicaoDTO;
import org.serratec.backend.ecommerce.exception.CategoriaException;
import org.serratec.backend.ecommerce.model.Categoria;
import org.serratec.backend.ecommerce.repository.CategoriaRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	ProdutoRepository produtoRepository;
	
	public Categoria dtoModel(CategoriaDTO categoriaDTO, Categoria categoria) {
		
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
		
		return categoria;
	}
	
	public CategoriaDTO modelDto(Categoria categoria, CategoriaDTO categoriaDTO) {
		
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
		
		return categoriaDTO;
	}
	
	public CategoriaExibicaoDTO modelToExibicao(Categoria categoria, CategoriaExibicaoDTO categoriaExibicaoDTO) {
		
		categoriaExibicaoDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaExibicaoDTO.setNomeCategoria(categoria.getNomeCategoria());
		categoriaExibicaoDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
		categoriaExibicaoDTO.setListaProduto(categoria.getListaProduto());
		
		return categoriaExibicaoDTO;
	}
	
	public String salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		dtoModel(categoriaDTO, categoria);
		categoriaRepository.save(categoria);
		return ">>  Categoria salva com sucesso!  <<";  
	}
	
	public List<CategoriaDTO> listarCategoria() {
		List<Categoria> listaCat = categoriaRepository.findAll();
		List<CategoriaDTO> listaCatDto = new ArrayList<>();
		
		for(Categoria categoria : listaCat) {
			CategoriaDTO catDto = new CategoriaDTO();
			modelDto(categoria, catDto);
			listaCatDto.add(catDto);
		}
		return listaCatDto;
		
	}
	
	public CategoriaExibicaoDTO buscarPorId(Integer idCategoria) throws CategoriaException {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
			Categoria ct = new Categoria();
			CategoriaExibicaoDTO ctDto = new CategoriaExibicaoDTO();
			if(categoria.isPresent()) {
				ct = categoria.get();
				modelToExibicao(ct, ctDto);
				return ctDto;
			}
			throw new CategoriaException(">> Categoria não encontrada com id informado! <<");
	}
	
	public String atualizar(Integer idCategoria, CategoriaDTO categoriaDTO) throws CategoriaException {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
		Categoria ct = new Categoria();
		
		if(categoria.isPresent()) {
			ct = categoria.get();
			
			if(categoriaDTO.getNomeCategoria() != null) {
				ct.setNomeCategoria(categoriaDTO.getNomeCategoria());
			}
			if(categoriaDTO.getDescricaoCategoria() !=null) {
				ct.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
			}
			categoriaRepository.save(ct);
			return ">> Categoria atualizada! <<";
		}
		throw new CategoriaException(">> Categoria não atualizada! <<");
			
	}
	
	public String deletar(Integer idCategoria ) {
		categoriaRepository.deleteById(idCategoria);
		return ">> Categoria excluida com sucesso! <<";
	}
	
	public String salvarVarios(List<CategoriaDTO> listaCatDto) {
		List<Categoria> listaCategoria = new ArrayList<>();
		
		for(CategoriaDTO categoriaDTO : listaCatDto) {
			Categoria categoria = new Categoria();
			dtoModel(categoriaDTO, categoria);
			listaCategoria.add(categoria);
		}
		categoriaRepository.saveAll(listaCategoria);
		return ">> Categorias salvas com sucesso! <<";
	}
	
	

}
