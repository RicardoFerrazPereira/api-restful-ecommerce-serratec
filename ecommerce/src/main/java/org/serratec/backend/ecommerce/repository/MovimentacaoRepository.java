package org.serratec.backend.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.ecommerce.DTO.RelatorioDTO;
import org.serratec.backend.ecommerce.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
	public default List<Movimentacao> findByNotaFiscal(String notaFiscal, List<Movimentacao> lista){
		  
		  List<Movimentacao> listaEncontrados = new ArrayList<>();
		  
		  for (Movimentacao movimentacao : lista) {
			if(movimentacao.getNotaFiscal().equals(notaFiscal)) {
				listaEncontrados.add(movimentacao);
			}
		}
		  
		  return listaEncontrados;
		  
	  }
		
		
		@Query(value="\r\n"
				+ "select \r\n"
				+ "PRODUTO_TX_NOME as nomeProduto,\r\n"
				+ "Sum(MOVIMENTACAO_QUANTIDADE_COMPRA ) as quantidadeVendida,\r\n"
				+ "Sum(MOVIMENTACAO_QUANTIDADE_COMPRA ) * PRODUTO_VALOR_UNITARIO  as valorTotal,\r\n"
				+ "from MOVIMENTACAO m\r\n"
				+ "inner join PRODUTO pr on (m.PRODUTO_ID = pr.PRODUTO_CD_ID)\r\n"
				+ "group by PRODUTO_ID \r\n"
				+ "order by quantidadeVendida desc\r\n"
				+ "limit 5", nativeQuery = true)
		List<RelatorioDTO> buscarMaisVendidos();

}
