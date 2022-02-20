package com.les.roupa.view.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.roupa.view.command.ICommand;
import com.les.roupa.view.helper.IViewHelper;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.view.command.impl.AlterarCommand;
import com.les.roupa.view.command.impl.ConsultarCommand;
import com.les.roupa.view.command.impl.ExcluirCommand;
import com.les.roupa.view.command.impl.SalvarCommand;
import com.les.roupa.view.helper.impl.CarrinhoHelper;
import com.les.roupa.view.helper.impl.CartaoCreditoHelper;
import com.les.roupa.view.helper.impl.ClienteHelper;
import com.les.roupa.view.helper.impl.CupomCarrinhoHelper;
import com.les.roupa.view.helper.impl.CupomHelper;
import com.les.roupa.view.helper.impl.DetalheProdutoHelper;
import com.les.roupa.view.helper.impl.EnderecoHelper;
import com.les.roupa.view.helper.impl.EstoqueHelper;
import com.les.roupa.view.helper.impl.GraficoAnaliseHelper;
import com.les.roupa.view.helper.impl.LoginHelper;
import com.les.roupa.view.helper.impl.PedidoHelper;
import com.les.roupa.view.helper.impl.PedidoTrocaHelper;
import com.les.roupa.view.helper.impl.ProdutoHelper;

/**
 * Respons�vel por processar todas as requisi��es feita pelo usuario,
 * configurado conforme o arquivo web.xml
 * @author Lorena Oliveira
 */
public class ControllerServlet extends HttpServlet {
	
	private static Map<String, IViewHelper> viewHelper;
	private static Map<String, ICommand> commands;
	
	// Construtor que inicializa os caminhos da URL
	public ControllerServlet() {
		
		// Mapa dos Commands de cada operacao
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		
		
		// Mapa das Views
		viewHelper = new HashMap<String, IViewHelper>();
		
		viewHelper.put("/eCommerce_roupa/cadastro", new ClienteHelper());
		viewHelper.put("/eCommerce_roupa/cadastroEndereco", new EnderecoHelper());
		viewHelper.put("/eCommerce_roupa/login", new LoginHelper());
		viewHelper.put("/eCommerce_roupa/cadastroCartao", new CartaoCreditoHelper());
		viewHelper.put("/eCommerce_roupa/cadastroProduto", new ProdutoHelper());
		viewHelper.put("/eCommerce_roupa/detalheProduto", new DetalheProdutoHelper());
		viewHelper.put("/eCommerce_roupa/carrinho", new CarrinhoHelper());
		viewHelper.put("/eCommerce_roupa/pedido", new PedidoHelper());
		viewHelper.put("/eCommerce_roupa/itemPedido", new PedidoHelper());
		viewHelper.put("/eCommerce_roupa/cupom", new CupomHelper());
		viewHelper.put("/eCommerce_roupa/cupomCarrinho", new CupomCarrinhoHelper());
		viewHelper.put("/eCommerce_roupa/trocaPedido", new PedidoTrocaHelper());
		viewHelper.put("/eCommerce_roupa/estoque", new EstoqueHelper());
		viewHelper.put("/eCommerce_roupa/graficoAnalise", new GraficoAnaliseHelper());
	}
	
	// Servlet Principal do sistema
	protected void processRequest (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
        // para aceitar acentuacao
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Obt�m a opera��o que ser� executada
        String operacao = request.getParameter("operacao");
        
        // Obt�m a uri que invocou esta servlet
        String uri = request.getRequestURI();
        
        // Obt�m uma viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = viewHelper.get(uri);
        
        // O View Helper retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade = vh.getEntidade(request);
        
        // Recupera o command correspondente com a operacao
        ICommand command = commands.get(operacao);
        
        // Executa o command que chamar� a fachada para executar a opera��o requisitada
        // o retorno � uma inst�ncia da classe resultado que pode conter mensagens de erro
        // ou entidades de retorno
        Resultado resultado = command.execute(entidade);
        
        // Executa o m�todo setView do view helper espec�fico para definir como dever� ser apresentado
        // o resultado para o usu�rio
        vh.setView(resultado, request, response);
	}
	
	// Method doGet que redireciona para o processRequest
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	// Method doPost que redireciona para o processRequest
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
}