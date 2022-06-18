package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.EnderecoDTO;
import org.serratec.backend.ecommerce.DTO.EnderecoExibicaoDTO;
import org.serratec.backend.ecommerce.DTO.EnderecoViaCepDTO;
import org.serratec.backend.ecommerce.exception.EnderecoException;
import org.serratec.backend.ecommerce.model.Endereco;
import org.serratec.backend.ecommerce.repository.ClienteRepository;
import org.serratec.backend.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	
	//DTO
		public EnderecoDTO modelToDTO(Endereco endereco, EnderecoDTO enderecoDTO){
			
			enderecoDTO.setIdEndereco(endereco.getIdEndereco());
			enderecoDTO.setCep(endereco.getCep());
			enderecoDTO.setComplemento(endereco.getComplemento());
			enderecoDTO.setIdCliente(endereco.getCliente().getIdCliente());
			enderecoDTO.setNumero(endereco.getNumero());			
			
			return enderecoDTO;
		}
		
		
		public Endereco dtoToModel(Endereco endereco, EnderecoDTO enderecoDTO){
			
			endereco.setComplemento(enderecoDTO.getComplemento());
			endereco.setNumero(enderecoDTO.getNumero());
			endereco.setCep(enderecoDTO.getCep());

			if(enderecoDTO.getIdCliente() != null) {
				endereco.setCliente(clienteRepository.findById(enderecoDTO.getIdCliente()).get());
			}
					
			return endereco;	
			
		}				
		
		public Endereco viaCepToModel(EnderecoViaCepDTO enderecoViaCepDTO, Endereco endereco) {
			
			endereco.setBairro(enderecoViaCepDTO.getBairro());
			endereco.setLogradouro(enderecoViaCepDTO.getLogradouro());
			endereco.setLocalidade(enderecoViaCepDTO.getLocalidade());
			endereco.setUf(enderecoViaCepDTO.getUf());	
			
			return endereco;
		}
		
		public EnderecoExibicaoDTO modelToDTO(EnderecoExibicaoDTO enderecoExibicaoDTO, Endereco endereco) {
			
			enderecoExibicaoDTO.setIdEndereco(endereco.getIdEndereco());
			enderecoExibicaoDTO.setIdCliente(endereco.getCliente().getIdCliente());
			enderecoExibicaoDTO.setLogradouro(endereco.getLogradouro());
			enderecoExibicaoDTO.setNumero(endereco.getNumero());
			enderecoExibicaoDTO.setComplemento(endereco.getComplemento());
			enderecoExibicaoDTO.setBairro(endereco.getBairro());
			enderecoExibicaoDTO.setLocalidade(endereco.getLocalidade());
			enderecoExibicaoDTO.setUf(endereco.getUf());
			enderecoExibicaoDTO.setCep(endereco.getCep());
			
			return enderecoExibicaoDTO;		
			
		}
		
		
	    //buscar o cep
		//colocar exception
		public EnderecoViaCepDTO buscarCep(String cep) {
			RestTemplate restTemplate = new RestTemplate();	        
		    String url = "http://viacep.com.br/ws/{cep}/json/";	    
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("cep", cep);	    
		    EnderecoViaCepDTO enderecoViaCepDTO = restTemplate.getForObject(url, EnderecoViaCepDTO.class, params);
		    
		    return enderecoViaCepDTO;
		}
		
		//salvar pelo cep
		public String salvarEndereco(EnderecoDTO enderecoDTO) {
			Endereco endereco = new Endereco();		
			dtoToModel(endereco, enderecoDTO);
			
			EnderecoViaCepDTO enderecoViaCepDTO = buscarCep(enderecoDTO.getCep());
			viaCepToModel(enderecoViaCepDTO, endereco);		
			
			enderecoRepository.save(endereco);
			
			return "Endereço salvp com sucesso com id " + endereco.getIdEndereco();
		}
	
		//buscar por id
		public EnderecoExibicaoDTO buscarPorId(Integer idEndereco) throws EnderecoException {
		Optional<Endereco> enderecoBuscado = enderecoRepository.findById(idEndereco);
		Endereco endereco = new Endereco();
		EnderecoExibicaoDTO enderecoExibicaoDTO = new EnderecoExibicaoDTO();	

		if (enderecoBuscado.isPresent()) {
			endereco = enderecoBuscado.get();
			modelToDTO(enderecoExibicaoDTO, endereco);			
			return enderecoExibicaoDTO;
		}
		
		throw new EnderecoException("Endereço com o id informado nao foi encontrado");
	}

		
	public List<EnderecoExibicaoDTO> buscarTodos() {
		List<Endereco> listaEnderecoModel = enderecoRepository.findAll();
		List<EnderecoExibicaoDTO> listaEnderecoDTO = new ArrayList<>();

		for (Endereco endereco : listaEnderecoModel) {
			EnderecoExibicaoDTO enderecoExibicaoDTO = new EnderecoExibicaoDTO();
			modelToDTO(enderecoExibicaoDTO, endereco);
			listaEnderecoDTO.add(enderecoExibicaoDTO);

		}
		return listaEnderecoDTO;

	}

	public String atualizar(Integer idEndereco, EnderecoExibicaoDTO enderecoExibicaoDTO) throws EnderecoException {
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
		Endereco atualizarEndereco = new Endereco();
		if (endereco.isPresent()) {
			atualizarEndereco = endereco.get();
			
			if(enderecoExibicaoDTO.getLogradouro() != null) {
				atualizarEndereco.setLogradouro(enderecoExibicaoDTO.getLogradouro());
			}
			
			if(enderecoExibicaoDTO.getNumero() != null) {
				atualizarEndereco.setNumero(enderecoExibicaoDTO.getNumero());;
			}
			
			if(enderecoExibicaoDTO.getComplemento() != null) {
				atualizarEndereco.setComplemento(enderecoExibicaoDTO.getComplemento());
			}
			
			if(enderecoExibicaoDTO.getBairro() != null) {
				atualizarEndereco.setBairro(enderecoExibicaoDTO.getBairro());
			}
			
			if(enderecoExibicaoDTO.getLocalidade() != null) {
				atualizarEndereco.setLocalidade(enderecoExibicaoDTO.getLocalidade());
			}
			
			if(enderecoExibicaoDTO.getUf() != null) {
				atualizarEndereco.setUf(enderecoExibicaoDTO.getUf());
			}
			
			if(enderecoExibicaoDTO.getCep() != null) {
				atualizarEndereco.setCep(enderecoExibicaoDTO.getCep());
			}
			
			enderecoRepository.save(atualizarEndereco);
			return "O endereço com o id " + atualizarEndereco.getIdEndereco() + " foi atualizado";
		}
		throw new EnderecoException("O endereco nao foi atualizado");
	}

	
	public void deletar(Integer idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}

}