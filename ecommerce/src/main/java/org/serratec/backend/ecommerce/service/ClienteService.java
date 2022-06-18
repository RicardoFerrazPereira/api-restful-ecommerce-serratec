package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.ClienteDTO;
import org.serratec.backend.ecommerce.DTO.ClienteExibicaoDTO;
import org.serratec.backend.ecommerce.exception.ClienteException;
import org.serratec.backend.ecommerce.model.Cliente;
import org.serratec.backend.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	// Model para DTO
	private ClienteDTO modelParaDTO(Cliente cliente, ClienteDTO clienteDTO) {
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setNomeCliente(cliente.getNomeCliente());
		clienteDTO.setClienteUsuario(cliente.getClienteUsuario());
		clienteDTO.setEmailCliente(cliente.getEmailCliente());
		clienteDTO.setCpfCliente(cliente.getCpfCliente());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setTelefoneCliente(cliente.getTelefoneCliente());
		clienteDTO.setTelefoneSec(cliente.getTelefoneSec());

		return clienteDTO;
	}

	// DTO para Model
	private Cliente dtoParaModel(Cliente cliente, ClienteDTO clienteDTO) {
		cliente.setNomeCliente(clienteDTO.getNomeCliente());
		cliente.setClienteUsuario(clienteDTO.getClienteUsuario());
		cliente.setEmailCliente(clienteDTO.getEmailCliente());
		cliente.setCpfCliente(clienteDTO.getCpfCliente());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
		cliente.setTelefoneSec(clienteDTO.getTelefoneSec());

		return cliente;
	}

	// Model Para Exibicao
	private ClienteExibicaoDTO modelParaExibicao(ClienteExibicaoDTO exibicaoCliente, Cliente cliente) {

		exibicaoCliente.setIdCliente(cliente.getIdCliente());
		exibicaoCliente.setNomeCliente(cliente.getNomeCliente());
		exibicaoCliente.setClienteUsuario(cliente.getClienteUsuario());
		exibicaoCliente.setEmailCliente(cliente.getEmailCliente());
		exibicaoCliente.setCpfCliente(cliente.getCpfCliente());
		exibicaoCliente.setDataNascimento(cliente.getDataNascimento());
		exibicaoCliente.setTelefoneCliente(cliente.getTelefoneCliente());
		exibicaoCliente.setTelefoneSec(cliente.getTelefoneSec());
		exibicaoCliente.setListaEndereco(cliente.getListaEndereco());
		exibicaoCliente.setListaPedidos(cliente.getListaPedidos());

		return exibicaoCliente;
	}

	// Salvar
	public String salvar(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		Cliente clienteSalvar = dtoParaModel(cliente, clienteDTO);
		clienteRepository.save(clienteSalvar);
		return "O cliente foi criado com o id: " + cliente.getIdCliente();
	}

	// Salvar Varios
	public void salvarListaCliente(List<ClienteDTO> lista) {
		List<Cliente> listaCliente = new ArrayList<>();

		for (ClienteDTO clienteDTO : lista) {
			Cliente cliente = new Cliente();
			dtoParaModel(cliente, clienteDTO);
			listaCliente.add(cliente);
		}

		clienteRepository.saveAll(listaCliente);
	}

	// Buscar Por Id
	public ClienteExibicaoDTO buscarPorId(Integer idCliente) throws ClienteException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		Cliente clienteNoBanco = new Cliente();

		ClienteExibicaoDTO exibicaoCliente = new ClienteExibicaoDTO();

		if (cliente.isPresent()) {
			clienteNoBanco = cliente.get();
			exibicaoCliente = modelParaExibicao(exibicaoCliente, clienteNoBanco);

			return exibicaoCliente;
		}

		throw new ClienteException("Cliente não encontrado");
	}

	// Buscar Todos
	public List<ClienteDTO> buscarTodos() {
		List<Cliente> listaCliente = clienteRepository.findAll();
		List<ClienteDTO> listaClienteDTO = new ArrayList<>();

		for (Cliente cliente : listaCliente) {

			ClienteDTO clienteDTO = new ClienteDTO();

			modelParaDTO(cliente, clienteDTO);
			listaClienteDTO.add(clienteDTO);
		}

		return listaClienteDTO;
	}

	// Atualizar
	public String atualizar(Integer idCliente, ClienteDTO clienteDTO) throws ClienteException {

		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		Cliente clienteNoBanco = new Cliente();

		if (cliente.isPresent()) {
			clienteNoBanco = cliente.get();
			if (clienteDTO.getNomeCliente() != null) {
				clienteNoBanco.setNomeCliente(clienteDTO.getNomeCliente());
			}
			if (clienteDTO.getClienteUsuario() != null) {
				clienteNoBanco.setClienteUsuario(clienteDTO.getClienteUsuario());
			}
			if (clienteDTO.getEmailCliente() != null) {
				clienteNoBanco.setEmailCliente(clienteDTO.getEmailCliente());
			}
			if (clienteDTO.getCpfCliente() != null) {
				clienteNoBanco.setCpfCliente(clienteDTO.getCpfCliente());
			}
			if (clienteDTO.getDataNascimento() != null) {
				clienteNoBanco.setDataNascimento(clienteDTO.getDataNascimento());
			}
			if (clienteDTO.getTelefoneCliente() != null) {
				clienteNoBanco.setTelefoneCliente(clienteDTO.getTelefoneCliente());
			}
			if (clienteDTO.getTelefoneSec() != null) {
				clienteNoBanco.setTelefoneSec(clienteDTO.getTelefoneSec());
			}
			clienteRepository.save(clienteNoBanco);
			return "O cliente com o id " + clienteNoBanco.getIdCliente() + " foi atualizado";
		}
			throw new ClienteException("O cliente nao foi atualizado");
	}

	// Deletar
	public void delete(Integer idCliente) {
		clienteRepository.deleteById(idCliente);
	}

}
