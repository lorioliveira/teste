package com.les.roupa.view.helper.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.view.helper.IViewHelper;

public class EnderecoHelper implements IViewHelper {
	
	Endereco endereco = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual opera��o do bot�o foi acionada - SALVAR/ALTERAR/CONSULTAR/EXCLUIR
		String operacao = request.getParameter("operacao");
		
		String id = null;
        String cep = null;
		String logradouro = null;
		String numero = null;
		String bairro = null;
        String cidade = null;
        String estado = null;
        String pais = null;
        String tipoResidencia = null;
        String observacoes = null;
        String tipoEndereco = null;
        String idCliente = null;
        
        String alteraEndereco = null;
        
     // salva a data atual na tabela de Cupom
     		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
     		Date date = new Date();
     		String dataAtual;
     		dataAtual = dateFormat.format(date);
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("id");
			
			endereco.setId(id);
		}
		
		else if (("SALVAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endere�o
			
			id = request.getParameter("id");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			estado = request.getParameter("estado");
			pais = request.getParameter("pais");
			tipoResidencia = request.getParameter("tipoResidencia");
			observacoes = request.getParameter("observacoes");
			tipoEndereco = request.getParameter("tipoEndereco");
			idCliente = request.getParameter("idCliente");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			
			// Atribuindo os valores que forma coletados do HTML para o Endere�o
			endereco.setId(id);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setObservacoes(observacoes);
			endereco.setTipoEnd(tipoEndereco);		
			endereco.setIdCliente(idCliente);
			endereco.setAlteraEndereco(alteraEndereco);
			endereco.setData_cadastro(dataAtual);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			endereco = new Endereco();
			
			// Atributos da classe endere�o
			id = request.getParameter("id");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			estado = request.getParameter("estado");
			pais = request.getParameter("pais");
			tipoResidencia = request.getParameter("tipoResidencia");
			observacoes = request.getParameter("observacoes");
			tipoEndereco = request.getParameter("tipoEndereco");
			alteraEndereco = request.getParameter("alteraEndereco");
			
			idCliente = request.getParameter("idCliente");
			
			// Atribuindo os valores que foram coletados do HTML para o Endere�o
			endereco.setId(id);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setTipoResidencia(tipoResidencia);
			endereco.setObservacoes(observacoes);
			endereco.setTipoEnd(tipoEndereco);
			endereco.setAlteraEndereco(alteraEndereco);
			endereco.setData_cadastro(dataAtual);
			
			endereco.setIdCliente(idCliente);
			
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			id = request.getParameter("id");
			
			endereco.setId(id);
		}
		
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Verifica qual opera��o do bot�o foi acionada
		String operacao = request.getParameter("operacao");
				
		// Usa para escrever na tela
		PrintWriter writer = response.getWriter();
		
		if (("CONSULTAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				
				// Redireciona para o arquivo .jsp
				request.getRequestDispatcher("JSP/enderecos.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// Guarda a mensagem que veio da Strategy na vari�vel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA CONSULTAR ENDERECO!");
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA CONSULTAR ENDERECO!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		
		else if (("SALVAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				// atribui a nova mensagem para poder mostra na pagina .JSP
				//resultado.setMensagem("Endere�o salvo com sucesso!");
				//writer.println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">");
				
				// Redireciona para o arquivo
				request.getRequestDispatcher("JSP/enderecos.jsp").forward(request, response);
			}
			else {
				// mostra as mensagens de ERRO se houver
				// Guarda a mensagem que veio da Strategy na vari�vel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA SALVAR ENDERECO!");
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA SALVAR ENDERECO!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			
			}
		}
		
		else if (("ALTERAR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String alteraEndereco = request.getParameter("alteraEndereco");
				String idEndereco = request.getParameter("id");

				// Se eu estiver pela tela de listagem de endere�os
				// n�o vou mandar o parametro "alteraEndereco" igual a zero, para poder chama o arquivo .JSP para edi��o do endere�o
				if (alteraEndereco.contentEquals("0")) {					
					// pendura o "idEndereco" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("idEndereco", idEndereco);
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/alterar-endereco.jsp").forward(request, response);
				}
				// caso contr�rio, se eu estiver pela tela de edi��o do endere�o,
				// vou ter/mandar o parametro "alteraEndereco" igual a um, para poder editar o endere�o,
				// dentro da DAO de endere�o, vai ter um IF verificando se tem o "alteraEndereco"
				else {
					
					// pendura o "resultado" na requisi��o para poder mandar para o arquivo .JSP
					request.setAttribute("mensagemStrategy", resultado.getMensagem());
					
					// Redireciona para o arquivo .jsp
					request.getRequestDispatcher("JSP/enderecos.jsp").forward(request, response);
				}
			
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// Guarda a mensagem que veio da Strategy na vari�vel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA ALTERAR ENDERECO!");
				
				/*
				 * writer.println(resultado.getMensagem());
				 * System.out.println("ERRO PARA ALTERAR!"); writer.
				 * println("<input type=\"button\" value=\"Voltar\" onclick=\"history.back()\">"
				 * );
				 */
			}
		}
		else if (("EXCLUIR").equals(operacao)) {
			if (resultado.getMensagem() == null || resultado.getMensagem().equals("")) {
				String idEndereco = request.getParameter("idEndereco");
				
				// pendura o "idEndereco" na requisi��o para poder mandar para o arquivo .JSP
				request.setAttribute("idEndereco", idEndereco);
				
				// Redireciona para o arquivo .jsp, para poder listar os endere�os novamente
				request.getRequestDispatcher("JSP/enderecos.jsp").forward(request, response);
			} 
			else {
				// mostra as mensagens de ERRO se houver
				// Guarda a mensagem que veio da Strategy na vari�vel para que 
				// seja exibida na tela 'tela-mensagem.jsp'
				request.setAttribute("mensagemStrategy", resultado.getMensagem());
				request.getRequestDispatcher("JSP/tela-mensagem.jsp").forward(request, response);
				System.out.println("ERRO PARA EXCLUIR ENDERECO!");
				
				
				// Redireciona para o arquivo .jsp
				//request.getRequestDispatcher("JSP/enderecos.jsp").forward(request, response);
			}
		}
	}

}