package org.serratec.backend.ecommerce.controller;

import java.util.List;

import org.serratec.backend.ecommerce.DTO.FuncionarioDTO;
import org.serratec.backend.ecommerce.DTO.FuncionarioExibicaoDTO;
import org.serratec.backend.ecommerce.exception.FuncionarioException;
import org.serratec.backend.ecommerce.service.FuncionarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/salvar")
	public ResponseEntity<String> salvarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO){
		return ResponseEntity.ok(funcionarioService.salvar(funcionarioDTO));
	}
	
	@PostMapping("/salvar-varios")
	public ResponseEntity<String> salvarVariosFuncionarios(@RequestBody List<FuncionarioDTO> listaFuncDto){
		return ResponseEntity.ok(funcionarioService.salvarVarios(listaFuncDto));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios() {
		return ResponseEntity.ok(funcionarioService.listarFuncionario());
	}
	
	@GetMapping("/buscar/{idFuncionario}")
	public ResponseEntity<FuncionarioExibicaoDTO> buscarFuncPorId(@PathVariable Integer idFuncionario) throws FuncionarioException {
		return ResponseEntity.ok(funcionarioService.buscarPorId(idFuncionario));
	}
	
	@PutMapping("/atualizar/{idFuncionario}")
	public ResponseEntity<String> atualizarFuncionario(@PathVariable Integer idFuncionario, @RequestBody FuncionarioDTO funcionarioDTO) throws FuncionarioException {
		return ResponseEntity.ok(funcionarioService.atualizar(idFuncionario, funcionarioDTO));
	}
	
	@DeleteMapping("/delete/{idFuncionario}")
	public ResponseEntity<String> deletarFuncionario(@PathVariable Integer idFuncionario){
		return ResponseEntity.ok(funcionarioService.deletar(idFuncionario));
	}	
	

}
