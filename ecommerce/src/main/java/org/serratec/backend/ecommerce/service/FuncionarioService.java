package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.FuncionarioDTO;
import org.serratec.backend.ecommerce.DTO.FuncionarioExibicaoDTO;
import org.serratec.backend.ecommerce.exception.FuncionarioException;
import org.serratec.backend.ecommerce.model.Funcionario;
import org.serratec.backend.ecommerce.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	public Funcionario dtoModel (FuncionarioDTO funcionarioDTO, Funcionario funcionario) {
		
		funcionario.setNomeFuncionario(funcionarioDTO.getNomeFuncionario());
		funcionario.setCpfFuncionario(funcionarioDTO.getCpfFuncionario());
		
		return funcionario;
	}
	
	public FuncionarioDTO modelDto (Funcionario funcionario, FuncionarioDTO funcionarioDTO) {
		
		funcionarioDTO.setIdFuncionario(funcionario.getIdFuncionario());
		funcionarioDTO.setNomeFuncionario(funcionario.getNomeFuncionario());
		funcionarioDTO.setCpfFuncionario(funcionario.getCpfFuncionario());
		
		return funcionarioDTO;
	}
	
	public FuncionarioExibicaoDTO modelParaExibicao(FuncionarioExibicaoDTO exibicaoFuncionario, Funcionario funcionario) {
		
		exibicaoFuncionario.setIdFuncionario(funcionario.getIdFuncionario());
		exibicaoFuncionario.setNomeFuncionario(funcionario.getNomeFuncionario());
		exibicaoFuncionario.setCpfFuncionario(funcionario.getCpfFuncionario());
		exibicaoFuncionario.setListaDeProdutos(funcionario.getListaDeProdutos());
		
		return exibicaoFuncionario;
	}
		
	public String salvar(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario();
		dtoModel(funcionarioDTO, funcionario);
		funcionarioRepository.save(funcionario);
		return " Funcionário criado com sucesso! ";
	}
	
	public List<FuncionarioDTO> listarFuncionario() {
		List<Funcionario> listaFunc = funcionarioRepository.findAll();
		List<FuncionarioDTO> listaFuncDTO = new ArrayList<>();
		
		for(Funcionario func : listaFunc) {
			FuncionarioDTO funcDTO = new FuncionarioDTO();
			modelDto(func, funcDTO);
			listaFuncDTO.add(funcDTO);
		}
		return listaFuncDTO;
			
		
	}
	public FuncionarioExibicaoDTO buscarPorId(Integer idFuncionario) throws FuncionarioException {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		Funcionario fc = new Funcionario();
		FuncionarioExibicaoDTO fcExib = new FuncionarioExibicaoDTO();
		
		if(funcionario.isPresent()) {
			fc = funcionario.get();
			modelParaExibicao(fcExib, fc);
			return fcExib;
		}
		throw new FuncionarioException("Funcionário não encontrado com id informado");
	}
		
	public String atualizar(Integer idFuncionario, FuncionarioDTO funcionarioDTO) throws FuncionarioException {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);
		Funcionario func = new Funcionario();
		
		if(funcionario.isPresent()) {
			func = funcionario.get();
			
			if(funcionarioDTO.getNomeFuncionario() != null) {
				func.setNomeFuncionario(funcionarioDTO.getNomeFuncionario());
			}
			if(funcionarioDTO.getCpfFuncionario() != null) {
				func.setCpfFuncionario(funcionarioDTO.getCpfFuncionario());
			}
			funcionarioRepository.save(func);
			return "O Funcionario com id " + func.getIdFuncionario() + " foi atualizado";
		}
		throw new FuncionarioException("O Funcionário não foi atualizado");
	}
	
	public String deletar(Integer idFuncionario) {
		funcionarioRepository.deleteById(idFuncionario);
		return "Funcionário excluído com sucesso";
	}
	
	public String salvarVarios(List<FuncionarioDTO> listaFuncDto) {
		List<Funcionario> listaFunc = new ArrayList<>();
		
		for (FuncionarioDTO funcionarioDTO : listaFuncDto) {
			Funcionario funcionario = new Funcionario();
			dtoModel(funcionarioDTO, funcionario);
			listaFunc.add(funcionario);
		}
		funcionarioRepository.saveAll(listaFunc);
		return "Funcionários salvos com sucesso!";
	}

}
