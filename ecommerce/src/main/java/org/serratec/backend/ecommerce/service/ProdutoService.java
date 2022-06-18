package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.ProdutoDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoExibicaoDTO;
import org.serratec.backend.ecommerce.DTO.RelatorioDTO;
import org.serratec.backend.ecommerce.exception.ProdutoException;
import org.serratec.backend.ecommerce.model.Produto;
import org.serratec.backend.ecommerce.repository.CategoriaRepository;
import org.serratec.backend.ecommerce.repository.FuncionarioRepository;
import org.serratec.backend.ecommerce.repository.MovimentacaoRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	//camada DTO
	public ProdutoDTO modelToDTO(Produto produto, ProdutoDTO produtoDTO) {
		
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setDataValidade(produto.getDataValidadeProduto());
		produtoDTO.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
		produtoDTO.setPeridoDeGarantia(produto.getPeridoDeGarantia());
		produtoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setIdFuncionario(produto.getFuncionario().getIdFuncionario());
		
				
		return produtoDTO;
	}
	
	public Produto dtoToModel(ProdutoDTO produtoDTO, Produto produto) {
		
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		produto.setDataValidadeProduto(produtoDTO.getDataValidade());
		produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());
		produto.setPeridoDeGarantia(produtoDTO.getPeridoDeGarantia());
		if(produtoDTO.getIdCategoria() != null) {
			produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).get());
		}
		
		if(produtoDTO.getIdFuncionario() != null) {
			produto.setFuncionario(funcionarioRepository.findById(produtoDTO.getIdFuncionario()).get());
		}
				
		return produto;
	}
	
	public ProdutoExibicaoDTO modelToDTOExibicao(Produto produto, ProdutoExibicaoDTO produtoExibicaoDTO) {
		
		produtoExibicaoDTO.setIdProduto(produto.getIdProduto());
		produtoExibicaoDTO.setNomeProduto(produto.getNomeProduto());
		produtoExibicaoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoExibicaoDTO.setValorUnitario(produto.getValorUnitario());
		produtoExibicaoDTO.setDataValidade(produto.getDataValidadeProduto());
		produtoExibicaoDTO.setQuantidadeEstoque(produto.getQuantidadeEmEstoque());
		produtoExibicaoDTO.setPeridoDeGarantia(produto.getPeridoDeGarantia());
		produtoExibicaoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoExibicaoDTO.setIdFuncionario(produto.getFuncionario().getIdFuncionario());
		produtoExibicaoDTO.setListaVendas(produto.getListaVendas());
		
		return produtoExibicaoDTO;
	}
	
	
	//buscar lista de produtos
		public List<ProdutoDTO> buscarTodos(){
			List<Produto> listaProdutos = produtoRepository.findAll();
			List<ProdutoDTO> listaProdutosDTO = new ArrayList<>();
			
			for (Produto produto : listaProdutos) {
				ProdutoDTO produtoDTO = new ProdutoDTO();
				modelToDTO(produto, produtoDTO);
				listaProdutosDTO.add(produtoDTO);			
			}
			
			return listaProdutosDTO;
			
		}
		
		//buscar produto por Id
		//vem junto a lista de itens
		public ProdutoExibicaoDTO buscarPorId(Integer idProduto) throws ProdutoException{
			Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
			ProdutoExibicaoDTO produtoExibicaoDTO = new ProdutoExibicaoDTO();
			
			if(produtoBuscado.isPresent()) {
				Produto produto = produtoBuscado.get();
				modelToDTOExibicao(produto, produtoExibicaoDTO);
				return produtoExibicaoDTO;
			}
			
			throw new ProdutoException("O produto com id informado não foi encontrado.");
			
		}
		
		//salvar um produto
		public String salvarProduto(ProdutoDTO produtoDTO) {
			Produto produto = new Produto();
			dtoToModel(produtoDTO, produto);
			produtoRepository.save(produto);
			
			return "Produto salvo com sucesso com id " + produto.getIdProduto();
		}
	
		
		//salvar lista de produtos
		public String salvarListaProdutos(List<ProdutoDTO> listaProdutoDTO) {
			List<Produto> listaProduto = new ArrayList<>();
			
			for(ProdutoDTO produtoDTO : listaProdutoDTO) {
				Produto produto = new Produto();
				dtoToModel(produtoDTO, produto);
				listaProduto.add(produto);
			}
			
			produtoRepository.saveAll(listaProduto);
			return "Todos os produtos foram salvos!";
		}
		
		//editar um produto
		public String editarProduto(Integer idProduto, ProdutoDTO produtoDTO) throws ProdutoException {
			Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
			
			if(produtoBuscado.isPresent()) {
				Produto produto = produtoBuscado.get();
				
				if(produtoDTO.getNomeProduto() != null) {
					produto.setNomeProduto(produtoDTO.getNomeProduto());
				}
				
				if(produtoDTO.getDescricaoProduto() != null) {
					produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
				}
				
				if(produtoDTO.getValorUnitario() != 0) {
					produto.setValorUnitario(produtoDTO.getValorUnitario());
				}
				
				if(produtoDTO.getDataValidade()!= null) {
					produto.setDataValidadeProduto(produtoDTO.getDataValidade());
				}
				
				if(produtoDTO.getQuantidadeEmEstoque() != null) {
					produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());
				}
				
				if(produtoDTO.getPeridoDeGarantia() != null) {
					produto.setPeridoDeGarantia(produtoDTO.getPeridoDeGarantia());
				}
				
				if(produtoDTO.getIdCategoria() != null) {
					produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).get());
				}
				
				if(produtoDTO.getIdFuncionario() != null) {
					produto.setFuncionario(funcionarioRepository.findById(produtoDTO.getIdFuncionario()).get());
				}
				
				produtoRepository.save(produto);
				return "Produto atualizado com sucesso!";
			}
			
			throw new ProdutoException("O id " + produtoDTO.getIdProduto() + " não foi encontrado.");
		
		}
		
		//deletar um produto
		public String deletarProduto(Integer idProduto) {
			produtoRepository.deleteById(idProduto);
			return "Produto deletado com sucesso";
		}
		
		
		public List<RelatorioDTO> relatorio(){
			return movimentacaoRepository.buscarMaisVendidos();
		}
}
