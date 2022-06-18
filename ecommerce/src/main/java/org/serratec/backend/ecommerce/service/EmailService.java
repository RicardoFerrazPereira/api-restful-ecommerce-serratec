package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.serratec.backend.ecommerce.DTO.MovimentacaoDTO;
import org.serratec.backend.ecommerce.DTO.MovimentacaoProdutoDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoDTO;
import org.serratec.backend.ecommerce.exception.EmailException;
import org.serratec.backend.ecommerce.model.Movimentacao;
import org.serratec.backend.ecommerce.repository.MovimentacaoRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.password}")
	private String senha;
	
	@Value("${spring.mail.email.remetente}")
	private String emailRemetente;

	
	
	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
		Properties prop = new Properties();

		enviarEmail.setHost(host);
		enviarEmail.setPort(465);
		enviarEmail.setUsername(userName);
		enviarEmail.setPassword(senha);
		enviarEmail.setProtocol("smtp");
		enviarEmail.setDefaultEncoding("UTF-8");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);
		enviarEmail.setJavaMailProperties(prop);
		return enviarEmail;

	}

	public void sendMessage(String to, String subject, String text) throws MessagingException, EmailException{

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailRemetente);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
	public void listaEmail(MovimentacaoDTO movimentacaoDTO) throws EmailException, MessagingException{
		
		List<Movimentacao> lista = movimentacaoRepository.findAll();
		List<Movimentacao> listaNotaFiscal = movimentacaoRepository.findByNotaFiscal(movimentacaoDTO.getNotaFiscal(), lista);
		List<MovimentacaoProdutoDTO> listaProduto = new ArrayList<>();
		
		for (Movimentacao movimentacao : listaNotaFiscal) {
			MovimentacaoProdutoDTO movimentacaoProdutoDTO = new MovimentacaoProdutoDTO();
			movimentacaoProdutoDTO.setIdProduto(movimentacao.getProduto().getIdProduto());
			movimentacaoProdutoDTO.setQuantidadeCompra(movimentacao.getQuantidadeCompra());
			movimentacaoProdutoDTO.setValorUnitario(movimentacao.getValorUnitario());
			
			listaProduto.add(movimentacaoProdutoDTO);
		}
		
		movimentacaoDTO.setListaProduto(listaProduto);
		
	}

	public void enviarEmail(MovimentacaoDTO movimentacaoDTO) throws EmailException, MessagingException{
		
			listaEmail(movimentacaoDTO);
			
			String listaDosProdutos = "";
			
			List<MovimentacaoProdutoDTO> listaProduto = movimentacaoDTO.getListaProduto();
			
			for (MovimentacaoProdutoDTO produto : listaProduto) {
				listaDosProdutos += "ID do produto: " + produto.getIdProduto().toString() + "<br/>" + "Quantidade: " + produto.getQuantidadeCompra().toString() + "<br/>" + "Valor Unitário: " + produto.getValorUnitario().toString() + "<br/>"; 	
			}		
			
			
			this.emailSender = javaMailSender();
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			try {
				helper.setFrom("tthurler10@gmail.com");
				helper.setTo(emailRemetente);
				
				helper.setSubject("Produtos Comprados");
				
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append("<html>"
	                    +"<body><img src='https://boards.plus4chan.org/cog/140245391900.jpg' "
	                    +"width='290' border='0' style='padding:22px;'>"
	                    +"<div style='font-family: fantasy;font-size:30px; color:rgb(6, 175, 79); font-weight:bold; border-style: groove; border-left: none; border-right: none; padding-left: 22px;'>"
	                    +"16 BITS</div>"
	                    +"<div style='font-family: monospace; font-size: 15px; color:rgb(0, 0, 0); padding-top: 20px;'>"
	                    +"Detalhes da Compra: <br/>"
	                    + listaDosProdutos
	                    +"<br/>"
	                    +"<br/><br/></div>"
	                    +"<div style='color:rgb(169, 166, 166);"
	                    +"font:size 12px;border-top-style:double;border-color:rgb(169, 166, 166);padding-top:5px'>"
	                    +"<i>Att, 16 Bits</i></div>"
	                    +"</body></html>'");
				helper.setText(sBuilder.toString(),true);
				
				emailSender.send(message);
			}catch (Exception e) {
				throw new EmailException("Houve um erro"+ e.getMessage());
			}
		}
	
public void emailEstoque(ProdutoDTO produtoDTO) throws EmailException, MessagingException {
		
		
		
		this.emailSender = javaMailSender();
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		try {
			helper.setFrom("tthurler10@gmail.com");
			helper.setTo(emailRemetente);
			
			helper.setSubject("Estoque");
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>"
                    +"<body><img src='https://boards.plus4chan.org/cog/140245391900.jpg' "
                    +"width='290' border='0' style='padding:22px;'>"
                    +"<div style='font-family: fantasy;font-size:30px; color:rgb(6, 175, 79); font-weight:bold; border-style: groove; border-left: none; border-right: none; padding-left: 22px;'>"
                    +"16 BITS</div>"
                    +"<div style='font-family: monospace; font-size: 15px; color:rgb(0, 0, 0); padding-top: 20px;'>"
                    +"Produtos em falta: <br/>"
                    +produtoDTO.getNomeProduto()
                    +"<br/>"
                    +"<br/><br/></div>"
                    +"<div style='color:rgb(169, 166, 166);"
                    +"font:size 12px;border-top-style:double;border-color:rgb(169, 166, 166);padding-top:5px'>"
                    +"<i>Att, 16 Bits</i></div>"
                    +"</body></html>'");
			helper.setText(sBuilder.toString(),true);
			
			emailSender.send(message);
		}catch (Exception e) {
			throw new EmailException("Houve um erro"+ e.getMessage());
		}
	}
}