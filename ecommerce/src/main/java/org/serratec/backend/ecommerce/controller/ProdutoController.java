package org.serratec.backend.ecommerce.controller;

import java.util.List;

import org.serratec.backend.ecommerce.DTO.CategoriaDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoExibicaoDTO;
import org.serratec.backend.ecommerce.DTO.RelatorioDTO;
import org.serratec.backend.ecommerce.exception.ProdutoException;
import org.serratec.backend.ecommerce.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<ProdutoDTO>> buscarTodos(){
		return ResponseEntity.ok(produtoService.buscarTodos());
	}
	
	@GetMapping("/busca/{idProduto}")
	public ResponseEntity<ProdutoExibicaoDTO> buscarPorId(@PathVariable Integer idProduto) throws ProdutoException{
		return ResponseEntity.ok(produtoService.buscarPorId(idProduto));
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<String> salvarProduto(@RequestBody ProdutoDTO produtoDTO){
		return ResponseEntity.ok(produtoService.salvarProduto(produtoDTO));
	}
	
	@PostMapping("/salvar-lista")
	public ResponseEntity<String>salvarListaProduto(@RequestBody List<ProdutoDTO> listaPRodutoDTO){
		return ResponseEntity.ok(produtoService.salvarListaProdutos(listaPRodutoDTO));
		
	}
	
	@PutMapping("/editar/{idProduto}")
	public ResponseEntity<String> editarProduto(@PathVariable Integer idProduto, @RequestBody ProdutoDTO produtoDTO) throws ProdutoException{
		return ResponseEntity.ok(produtoService.editarProduto(idProduto, produtoDTO));
	}
	
	@DeleteMapping("/deletar/{idProduto}")
		public ResponseEntity<String> deletarProduto(@PathVariable Integer idProduto){
			return ResponseEntity.ok(produtoService.deletarProduto(idProduto));
	}
	
	@GetMapping("/relatorio")
	public ResponseEntity<List<RelatorioDTO>> relatorio(){
		return ResponseEntity.ok(produtoService.relatorio());
	}
}
