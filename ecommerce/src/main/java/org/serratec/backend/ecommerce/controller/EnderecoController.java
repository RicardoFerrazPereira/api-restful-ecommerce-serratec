package org.serratec.backend.ecommerce.controller;

import java.util.List;

import org.serratec.backend.ecommerce.DTO.EnderecoDTO;
import org.serratec.backend.ecommerce.DTO.EnderecoExibicaoDTO;
import org.serratec.backend.ecommerce.DTO.EnderecoViaCepDTO;
import org.serratec.backend.ecommerce.exception.EnderecoException;
import org.serratec.backend.ecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping("/{cep}")
	  public ResponseEntity<EnderecoViaCepDTO> buscarCep(@PathVariable String cep) {
		return ResponseEntity.ok(enderecoService.buscarCep(cep));
	  }

	@PostMapping("/salvar")
	public ResponseEntity<String> salvarEndereco(@RequestBody EnderecoDTO enderecoDTO){
		return ResponseEntity.ok(enderecoService.salvarEndereco(enderecoDTO));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<EnderecoExibicaoDTO>> listarTodos(){
		return ResponseEntity.ok(enderecoService.buscarTodos());
	}
	
	@GetMapping("/buscar/{idEndereco}")
	public ResponseEntity<EnderecoExibicaoDTO> buscarPorId(@PathVariable Integer idEndereco) throws EnderecoException{
		return ResponseEntity.ok(enderecoService.buscarPorId(idEndereco));
	}
	
	
	@PutMapping("/atualizar/{idEndereco}")
	public ResponseEntity<String> atualizar(@PathVariable Integer idEndereco, @RequestBody EnderecoExibicaoDTO enderecoExibicaoDTO) throws EnderecoException {
		return ResponseEntity.ok(enderecoService.atualizar(idEndereco, enderecoExibicaoDTO));
	}
	
	
	@DeleteMapping("/{idEndereco}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idEndereco){
		enderecoService.deletar(idEndereco);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}

