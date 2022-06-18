package org.serratec.backend.ecommerce.controller;

import java.util.List;

import org.serratec.backend.ecommerce.DTO.CategoriaDTO;
import org.serratec.backend.ecommerce.DTO.CategoriaExibicaoDTO;
import org.serratec.backend.ecommerce.exception.CategoriaException;
import org.serratec.backend.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@PostMapping("/salvar")
	public ResponseEntity<String> salvarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		return ResponseEntity.ok(categoriaService.salvar(categoriaDTO));
		
	}
	@PostMapping("/salvar-varios")
	public ResponseEntity<String>salvarCategorias(@RequestBody List<CategoriaDTO> listaCatDto){
		return ResponseEntity.ok(categoriaService.salvarVarios(listaCatDto));
		
	}
	@GetMapping("/listar")
	public ResponseEntity<List<CategoriaDTO>> listarCategoria() {
		return ResponseEntity.ok(categoriaService.listarCategoria());
	}
	
	@GetMapping("/buscar/{idCategoria}")
	public ResponseEntity<CategoriaExibicaoDTO> buscarCategoriaId(@PathVariable Integer idCategoria) throws CategoriaException {
		return ResponseEntity.ok(categoriaService.buscarPorId(idCategoria));
	}
	
	@PutMapping("/atualizar/{idCategoria}")
	public ResponseEntity<String> atualizarCategoria(@PathVariable Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO) throws CategoriaException {
		return ResponseEntity.ok(categoriaService.atualizar(idCategoria, categoriaDTO));
	}
	
	@DeleteMapping("/delete/{idCategoria}")
	public ResponseEntity<String> deletarCategoria(@PathVariable Integer idCategoria) {
		return ResponseEntity.ok(categoriaService.deletar(idCategoria));
	}	

}
