package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.backend.ecommerce.DTO.MovimentacaoDTO;
import org.serratec.backend.ecommerce.DTO.MovimentacaoProdutoDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoDTO;
import org.serratec.backend.ecommerce.exception.EmailException;
import org.serratec.backend.ecommerce.exception.MovimentacaoException;
import org.serratec.backend.ecommerce.model.Movimentacao;
import org.serratec.backend.ecommerce.model.Produto;
import org.serratec.backend.ecommerce.repository.ClienteRepository;
import org.serratec.backend.ecommerce.repository.MovimentacaoRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	EmailService emailService;

	//camada DTO
		public MovimentacaoDTO modelToDTO(Movimentacao movimentacao, MovimentacaoDTO movimentacaoDTO) {
				
			movimentacaoDTO.setIdMovimentacao(movimentacao.getIdMovimentacao());
			movimentacaoDTO.setIdCliente(movimentacao.getCliente().getIdCliente());
			movimentacaoDTO.setNotaFiscal(movimentacao.getNotaFiscal());
			movimentacaoDTO.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
			
			List<Movimentacao> listaMovimentacoes = movimentacaoRepository.findAll();			
			List<Movimentacao> listaPorNotaFiscal = movimentacaoRepository.findByNotaFiscal(movimentacaoDTO.getNotaFiscal(), listaMovimentacoes);
			List<MovimentacaoProdutoDTO> listaProdutos = new ArrayList<>();
			
			for (Movimentacao movimentacao2 : listaPorNotaFiscal) {
				MovimentacaoProdutoDTO compraDTO = new MovimentacaoProdutoDTO();
				compraDTO.setQuantidadeCompra(movimentacao2.getQuantidadeCompra());
				compraDTO.setValorUnitario(movimentacao2.getValorUnitario());
				compraDTO.setIdProduto(movimentacao2.getProduto().getIdProduto());
				listaProdutos.add(compraDTO);
				
			}
						
			movimentacaoDTO.setListaProduto(listaProdutos);
			return movimentacaoDTO;
			
			}
			
		public Movimentacao DTOToModel(MovimentacaoDTO movimentacaoDTO, Movimentacao movimentacao) throws MovimentacaoException {
			
			movimentacao.setNotaFiscal(movimentacaoDTO.getNotaFiscal());		
			movimentacao.setTipoMovimentacao(movimentacaoDTO.getTipoMovimentacao());								
			
			if(movimentacaoDTO.getIdCliente() != null) {
				
				movimentacao.setCliente(clienteRepository.findById(movimentacaoDTO.getIdCliente()).get());
			}
			
			
				return movimentacao;
		}

		//buscar lista de movimentações
		public List<MovimentacaoDTO> buscarTodas(){
			List<Movimentacao> listaMovimentacao = movimentacaoRepository.findAll();
			List<MovimentacaoDTO> listaMovimentacaoDTO = new ArrayList<>();
					
			for (Movimentacao movimentacao : listaMovimentacao) {
				MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
				modelToDTO(movimentacao, movimentacaoDTO);
				listaMovimentacaoDTO.add(movimentacaoDTO);			
			}
								
			return listaMovimentacaoDTO;
					
		}
		
		//buscar por nota fiscal
		public List<MovimentacaoDTO> buscarPorNotaFiscal(String notaFiscal) throws MovimentacaoException{
			
			List<Movimentacao> listaMovimentacao = movimentacaoRepository.findAll();
			List<Movimentacao> listaPorNotaFiscal = movimentacaoRepository.findByNotaFiscal(notaFiscal, listaMovimentacao);
			List<MovimentacaoDTO> listaMovimentacaoDTO = new ArrayList<>();
			
			if(listaPorNotaFiscal.isEmpty()) {
				throw new MovimentacaoException("Nota fiscal não encontrada!");
			}
			
			for (Movimentacao movimentacao : listaPorNotaFiscal) {
				MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
				modelToDTO(movimentacao, movimentacaoDTO);
				listaMovimentacaoDTO.add(movimentacaoDTO);			
			}
					
			return listaMovimentacaoDTO;
					
		}
		
		public void faltaDeEstoque(MovimentacaoDTO movimentacaoDTO)  {
			movimentacaoDTO.getListaProduto().forEach(produto -> {
				
				Produto produtoEstoque = produtoRepository.findById(produto.getIdProduto()).get();
				
				produtoEstoque.setQuantidadeEmEstoque(produtoEstoque.getQuantidadeEmEstoque() - produto.getQuantidadeCompra());
				produtoRepository.save(produtoEstoque);
				
				if(produtoEstoque.getQuantidadeEmEstoque() < 6) {
					ProdutoDTO produtoDTO = new ProdutoDTO();
					produtoDTO.setNomeProduto(produtoEstoque.getNomeProduto());
					
					try {
						emailService.emailEstoque(produtoDTO);
					} catch (EmailException e) {
					
						e.printStackTrace();
					} catch (MessagingException e) {
						
						e.printStackTrace();
					}
					
				}
			});	
			
		}
		
		//salvar uma movimentacao		
		public String salvarMovimentacao(MovimentacaoDTO movimentacaoDTO) throws MovimentacaoException, EmailException, MessagingException {
			
			
			for (MovimentacaoProdutoDTO movimentacaoProdutoDTO : movimentacaoDTO.getListaProduto()) {
					
				Movimentacao movimentacao = new Movimentacao();			
				movimentacao.setProduto(produtoRepository.findById(movimentacaoProdutoDTO.getIdProduto()).get());
				movimentacao.setValorUnitario(movimentacaoProdutoDTO.getValorUnitario());
				movimentacao.setQuantidadeCompra(movimentacaoProdutoDTO.getQuantidadeCompra());
									
				if(movimentacaoProdutoDTO.getQuantidadeCompra() > movimentacao.getProduto().getQuantidadeEmEstoque()) {
					throw new MovimentacaoException("Falta de estoque!");
				
				}
				faltaDeEstoque(movimentacaoDTO);
				
				DTOToModel(movimentacaoDTO, movimentacao);		
				movimentacaoRepository.save(movimentacao);
				
				
			}
			
			emailService.enviarEmail(movimentacaoDTO);		
			return "Movimentacao salva com sucesso";
					
		}
		
		//editar uma movimentação
		public String editarMovimentacao(Integer idMovimentacao, MovimentacaoDTO movimentacaoDTO) throws MovimentacaoException {
			Optional<Movimentacao> movimentacaoBuscada = movimentacaoRepository.findById(idMovimentacao);
					
			if(movimentacaoBuscada.isPresent()) {
				Movimentacao movimentacao = movimentacaoBuscada.get();
						
				if(movimentacaoDTO.getIdCliente() != null) {
					movimentacao.setCliente(clienteRepository.findById(movimentacaoDTO.getIdCliente()).get());
				}
						
				if(movimentacaoDTO.getNotaFiscal() != null) {
					movimentacao.setNotaFiscal(movimentacaoDTO.getNotaFiscal());
				}
						
						
				if(movimentacaoDTO.getTipoMovimentacao() != null) {
					movimentacao.setTipoMovimentacao(movimentacaoDTO.getTipoMovimentacao() );
				}
						
				if(movimentacaoDTO.getListaProduto() != null) {
					for (MovimentacaoProdutoDTO movimentacaoProdutoDTO : movimentacaoDTO.getListaProduto()) {
						movimentacao.setProduto(produtoRepository.findById(movimentacaoProdutoDTO.getIdProduto()).get());
						movimentacao.setValorUnitario(movimentacaoProdutoDTO.getValorUnitario());
						movimentacao.setQuantidadeCompra(movimentacaoProdutoDTO.getQuantidadeCompra());
					}		
				}				
						
				movimentacaoRepository.save(movimentacao);
				return "Categoria atualizada com sucesso!";
			}
					
			throw new MovimentacaoException("O id " + movimentacaoDTO.getIdMovimentacao() + " não foi encontrado.");
				
		}
		
		//deletar uma movimentacao
		public String deletarMovimentacao(Integer idMovimentacao) {
			movimentacaoRepository.deleteById(idMovimentacao);
			return "Funcionário deletado com sucesso";
		}

}
