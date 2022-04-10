package com.les.roupa.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.les.roupa.core.dao.IDAO;
import com.les.roupa.core.dao.impl.CarrinhoDAO;
import com.les.roupa.core.dao.impl.CartaoCreditoDAO;
import com.les.roupa.core.dao.impl.ClienteDAO;
import com.les.roupa.core.dao.impl.CupomDAO;
import com.les.roupa.core.dao.impl.DetalheProdutoDAO;
import com.les.roupa.core.dao.impl.EnderecoDAO;
import com.les.roupa.core.dao.impl.LoginDAO;
import com.les.roupa.core.dao.impl.PedidoDAO;
import com.les.roupa.core.dao.impl.PedidoTrocaDAO;
import com.les.roupa.core.dao.impl.ProdutoDAO;
import com.les.roupa.core.dao.impl.VerificaCupomDAO;
import com.les.roupa.core.fachada.IFachada;
import com.les.roupa.core.dominio.Carrinho;
import com.les.roupa.core.dominio.CartaoCredito;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.Cupom;
import com.les.roupa.core.dominio.DetalheProduto;
import com.les.roupa.core.dominio.Endereco;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Pedido;
import com.les.roupa.core.dominio.PedidoTroca;
import com.les.roupa.core.dominio.Produto;
import com.les.roupa.core.dominio.Resultado;
import com.les.roupa.core.dominio.Usuario;
import com.les.roupa.core.dominio.VerificaCupom;
import com.les.roupa.core.strategy.impl.ValidarBairro;
import com.les.roupa.core.strategy.impl.ValidarBairro_Alt;
import com.les.roupa.core.strategy.impl.ValidarBandeiraCartao;
import com.les.roupa.core.strategy.impl.ValidarCEP;
import com.les.roupa.core.strategy.impl.ValidarCEP_Alt;
import com.les.roupa.core.strategy.impl.ValidarCPF;
import com.les.roupa.core.strategy.impl.ValidarCPF_Alt;
import com.les.roupa.core.strategy.impl.ValidarCartaoPedido;
import com.les.roupa.core.strategy.impl.ValidarCidade;
import com.les.roupa.core.strategy.impl.ValidarCidade_Alt;
import com.les.roupa.core.strategy.impl.ValidarCupom;
import com.les.roupa.core.strategy.impl.ValidarDataNascimento;
import com.les.roupa.core.strategy.impl.ValidarDataNascimento_Alt;
import com.les.roupa.core.strategy.impl.ValidarDtCadastro;
import com.les.roupa.core.strategy.impl.ValidarEmail;
import com.les.roupa.core.strategy.impl.ValidarEnderecoPedido;
import com.les.roupa.core.strategy.impl.ValidarEstado;
import com.les.roupa.core.strategy.impl.ValidarEstado_Alt;
import com.les.roupa.core.strategy.impl.ValidarExisteEmail;
import com.les.roupa.core.strategy.impl.ValidarExisteEmailSenha;
import com.les.roupa.core.strategy.impl.ValidarFormaDePagamento;
import com.les.roupa.core.strategy.impl.ValidarGenero;
import com.les.roupa.core.strategy.impl.ValidarGenero_Alt;
import com.les.roupa.core.strategy.impl.ValidarLogradouro;
import com.les.roupa.core.strategy.impl.ValidarLogradouro_Alt;
import com.les.roupa.core.strategy.impl.ValidarNome;
import com.les.roupa.core.strategy.impl.ValidarNome_Alt;
import com.les.roupa.core.strategy.impl.ValidarNumCartao;
import com.les.roupa.core.strategy.impl.ValidarNumero;
import com.les.roupa.core.strategy.impl.ValidarNumero_Alt;
import com.les.roupa.core.strategy.impl.ValidarPais;
import com.les.roupa.core.strategy.impl.ValidarPais_Alt;
import com.les.roupa.core.strategy.impl.ValidarSenha;
import com.les.roupa.core.strategy.impl.ValidarSenhaConfSenha;
import com.les.roupa.core.strategy.impl.ValidarSenhaLogin;
import com.les.roupa.core.strategy.impl.ValidarStatus;
import com.les.roupa.core.strategy.impl.ValidarTelefone;
import com.les.roupa.core.strategy.impl.ValidarTipoResidencia;
import com.les.roupa.core.strategy.impl.ValidarTipoResidencia_Alt;
import com.les.roupa.core.strategy.impl.ValidarTotalPedido;
import com.les.roupa.view.helper.impl.DetalheProdutoHelper;
import com.les.roupa.core.strategy.IStrategy;


/**
 * FACHADA - Mirror Fashion
 * @author Lorena Oliveira
*/

public class Fachada implements IFachada {

	private Resultado resultado;
	private static Map<String, IDAO> daos;

	/* ------------ Declaracao de TODAS as Strategy's ------------ */
	
	/* ---- SALVAR/ALTERAR CLIENTE -----*/
	ValidarEmail vEmail = new ValidarEmail();
	ValidarNome vNome = new ValidarNome();
	//ValidarCPF vCPF = new ValidarCPF();
	ValidarDataNascimento vDt_nasc = new ValidarDataNascimento();
	ValidarTelefone vTelefone = new ValidarTelefone();
	ValidarGenero vGenero = new ValidarGenero();
	ValidarStatus vStatus = new ValidarStatus();
	ValidarSenhaConfSenha vSenhaConfSenhaIgual = new ValidarSenhaConfSenha();
	ValidarSenha vSenha = new ValidarSenha();
	
	//ValidarCPF_Alt vCPFAlterado = new ValidarCPF_Alt();
	ValidarNome_Alt vNomeAlterado = new ValidarNome_Alt();
	ValidarDataNascimento_Alt vDtNascimentoAlterado = new ValidarDataNascimento_Alt();
	ValidarGenero_Alt vGeneroAlterado = new ValidarGenero_Alt();


	/* ---- SALVAR/ALTERAR ENDERE�O ---- */
	//ValidarCEP vcep = new ValidarCEP();
	ValidarLogradouro vLogradouro = new ValidarLogradouro();
	ValidarNumero vNumero = new ValidarNumero();
	ValidarBairro vBairro = new ValidarBairro();
	ValidarCidade vCidade = new ValidarCidade();
	ValidarEstado vEstado = new ValidarEstado();
	ValidarPais vPais = new ValidarPais();
	ValidarTipoResidencia vTipoResidencia = new ValidarTipoResidencia();
	

	ValidarBairro_Alt vBairroAlterado = new ValidarBairro_Alt();
	ValidarCEP_Alt vCEPAlterado = new ValidarCEP_Alt();
	ValidarLogradouro_Alt vLogradouroAlterado = new ValidarLogradouro_Alt();
	ValidarNumero_Alt vNumAlterado = new ValidarNumero_Alt();
	ValidarCidade_Alt vCidadeAlterado = new ValidarCidade_Alt();
	ValidarEstado_Alt vEstadoAlterado = new ValidarEstado_Alt();
	ValidarPais_Alt vPaisAlterado = new ValidarPais_Alt();
	ValidarTipoResidencia_Alt vTipoResidenciaAlterado = new ValidarTipoResidencia_Alt();
	
	/* ---------- PEDIDO ------------ */
	ValidarTotalPedido vTotalPedido = new ValidarTotalPedido();
	ValidarEnderecoPedido vEnderecoPedido = new ValidarEnderecoPedido();
	ValidarFormaDePagamento vFormaDePagamentoPedido = new ValidarFormaDePagamento();
	ValidarCartaoPedido vCartaoPedido = new ValidarCartaoPedido();
	
	/* ---- SALVAR/ALTERAR CARTAO DE CREDITO -----*/
	ValidarNumCartao vNumCartao = new ValidarNumCartao();
	ValidarBandeiraCartao vBandeiraCartao = new ValidarBandeiraCartao();
//	ValidarCVV vCVV = new ValidarCVV();
//	ValidarNomeCartao vNomeCartao = new ValidarNomeCartao();
	
//	ValidarNomeCartao_Alt vNomeCartaoAlterado = new ValidarNomeCartao_Alt();
//	ValidarCVV_Alt vCVVAlterado = new ValidarCVV_Alt();
//	ValidarNumeroCartao_Alt vNumCartaoAlterado = new ValidarNumeroCartao_Alt();
		
	/* ---- LOGIN -----*/
	ValidarExisteEmail vExisteEmail = new ValidarExisteEmail();
	ValidarExisteEmailSenha vExisteEmailSenha = new ValidarExisteEmailSenha();
	ValidarSenhaLogin vSenhaLogin = new ValidarSenhaLogin();
	
	/* ---- PRODUTO -----*/
//	ValidarNomeProduto vNomeProduto = new ValidarNomeProduto();
//	ValidarCategoria vCategoria = new ValidarCategoria();
//	ValidarPrecoCompra vPrecoCompra = new ValidarPrecoCompra();
//	ValidarPrecoVenda vPrecoVenda = new ValidarPrecoVenda();
//	ValidarDtCadastro vDtCadastro = new ValidarDtCadastro();
//	ValidarFoto vFoto = new ValidarFoto();
//	ValidarQtdeProduto vQtde = new ValidarQtdeProduto();
//	ValidarDescricao vDescricao = new ValidarDescricao();
//	ValidarStatusProduto vStatusProduto = new ValidarStatusProduto();
//	ValidarGrupoPrecificacao vGrupoPrecificacao = new ValidarGrupoPrecificacao();	
	
	/* --- CUPOM --- */
	ValidarCupom vCupom = new ValidarCupom();
	
	
	/* --- ESTOQUE - ENTRADA E SAIDA --- */
	//ValidarEntradaEstoque vEntradaEstoque = new ValidarEntradaEstoque();
	//ValidarSaidaEstoque vSaidaEstoque = new ValidarSaidaEstoque();
	
	/* --- GRAFICO --- */
	//ValidarDatasGraficoAnalise vGrafico = new ValidarDatasGraficoAnalise();
	
	/* ------------------------------------------------------------------------------------------------------------------- */
	
	/* ------------ Declaracao das Listas de Strategy's dos Dominios ------------ */
	/* ------------ SALVAR ------------ */
	List<IStrategy> regrasSalvarCliente = new ArrayList<>();
	List<IStrategy> regrasSalvarEndereco = new ArrayList<>();
	List<IStrategy> regrasSalvarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasSalvarLogin = new ArrayList<>();
	List<IStrategy> regrasSalvarProduto = new ArrayList<>();
	List<IStrategy> regrasSalvarDetalheProduto = new ArrayList<>();
	List<IStrategy> regrasSalvarCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarPedido = new ArrayList<>();
	List<IStrategy> regrasSalvarCupom = new ArrayList<>();
	List<IStrategy> regrasSalvarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasSalvarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasSalvarEstoque = new ArrayList<>();
	List<IStrategy> regrasSalvarGrafico = new ArrayList<>();
	
	/* ------------ CONSULTAR ------------ */
	List<IStrategy> regrasConsultarCliente = new ArrayList<>();
	List<IStrategy> regrasConsultarEndereco = new ArrayList<>();
	List<IStrategy> regrasConsultarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasConsultarLogin = new ArrayList<>();
	List<IStrategy> regrasConsultarProduto = new ArrayList<>();
	List<IStrategy> regrasConsultarCarrinho = new ArrayList<>();
	List<IStrategy> regrasConsultarPedido = new ArrayList<>();
	List<IStrategy> regrasConsultarCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasConsultarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasConsultarEstoque = new ArrayList<>();
	List<IStrategy> regrasConsultarGrafico = new ArrayList<>();
	
	/* ------------ ALTERAR ------------ */
	List<IStrategy> regrasAlterarCliente = new ArrayList<>();
	List<IStrategy> regrasAlterarEndereco = new ArrayList<>();
	List<IStrategy> regrasAlterarCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasAlterarLogin = new ArrayList<>();
	List<IStrategy> regrasAlterarProduto = new ArrayList<>();
	List<IStrategy> regrasAlterarCarrinho = new ArrayList<>();
	List<IStrategy> regrasAlterarPedido = new ArrayList<>();
	List<IStrategy> regrasAlterarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasAlterarCupom = new ArrayList<>();
	List<IStrategy> regrasAlterarCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasAlterarPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasAlterarEstoque = new ArrayList<>();
	List<IStrategy> regrasAlterarGrafico = new ArrayList<>();
	
	/* ------------ EXCLUIR ------------ */
	List<IStrategy> regrasExcluirCliente = new ArrayList<>();
	List<IStrategy> regrasExcluirEndereco = new ArrayList<>();
	List<IStrategy> regrasExcluirCartaoCredito = new ArrayList<>();
	List<IStrategy> regrasExcluirLogin = new ArrayList<>();
	List<IStrategy> regrasExcluirProduto = new ArrayList<>();
	List<IStrategy> regrasExcluirCarrinho = new ArrayList<>();
	List<IStrategy> regrasExcluirPedido = new ArrayList<>();
	List<IStrategy> regrasExcluirCupom = new ArrayList<>();
	List<IStrategy> regrasExcluirVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasExcluirCupomCarrinho = new ArrayList<>();
	List<IStrategy> regrasExcluirPedidoTroca = new ArrayList<>();
	List<IStrategy> regrasExcluirEstoque = new ArrayList<>();
	List<IStrategy> regrasExcluirGrafico= new ArrayList<>();
	
	/* ------------------------------------------------------------------------------------------------------------------- */
	
	/* ------------ Declaracao dos MAP's das Regras de Negocios dos Dominios ------------ */
	Map<String, List<IStrategy>> regrasCliente = new HashMap<>();
	Map<String, List<IStrategy>> regrasEndereco = new HashMap<>();
	Map<String, List<IStrategy>> regrasCartaoCredito = new HashMap<>();
	Map<String, List<IStrategy>> regrasLogin = new HashMap<>();
	Map<String, List<IStrategy>> regrasProduto = new HashMap<>();
	Map<String, List<IStrategy>> regrasDetalheProduto = new HashMap<>();
	Map<String, List<IStrategy>> regrasCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedido = new HashMap<>();
	Map<String, List<IStrategy>> regrasCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasVerificaCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasCupomCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedidoTroca = new HashMap<>();
	Map<String, List<IStrategy>> regrasEstoque = new HashMap<>();
	Map<String, List<IStrategy>> regrasGrafico = new HashMap<>();
	
	/* ------------------------------------------------------------------------------------------------------------------ */
	
	/* ------------ Declaracao da Regra de Negocio Geral ------------ */
	
	Map<String, Map<String, List<IStrategy>>> regrasGeral = new HashMap<>();
	
	/* ------------------------------------------------------------------------------------------------------------------- */

	// CONSTRUTOR DA FACHADA
	public Fachada() {
		// Mapa dos DAO's
		daos = new HashMap<String, IDAO>();

		// Criando instancias dos DAOS a serem utilizados,
		// adicionando cada dado no MAP indexado pelo nome da classe correspondente
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Endereco.class.getName(), new EnderecoDAO());
		daos.put(CartaoCredito.class.getName(), new CartaoCreditoDAO());
		daos.put(Usuario.class.getName(), new LoginDAO());
		daos.put(Produto.class.getName(), new ProdutoDAO());
		daos.put(DetalheProduto.class.getName(), new DetalheProdutoDAO());
		daos.put(Carrinho.class.getName(), new CarrinhoDAO());
		daos.put(Pedido.class.getName(), new PedidoDAO());
		daos.put(Cupom.class.getName(), new CupomDAO());
		daos.put(VerificaCupom.class.getName(), new VerificaCupomDAO());
		daos.put(PedidoTroca.class.getName(), new PedidoTrocaDAO());
		
		//daos.put(CupomCarrinho.class.getName(), new CupomCarrinhoDAO());
		//daos.put(Estoque.class.getName(), new EstoqueDAO());
		//daos.put(GraficoAnalise.class.getName(), new GraficoAnaliseDAO());
		
	/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do CLIENTE ----- */
		
		/* ----- SALVAR ----- */
		regrasSalvarCliente.add(vNome);
		//regrasSalvarCliente.add(vCPF);
		regrasSalvarCliente.add(vEmail);
		regrasSalvarCliente.add(vExisteEmail);
		regrasSalvarCliente.add(vSenha);
		regrasSalvarCliente.add(vDt_nasc);
		regrasSalvarCliente.add(vTelefone);
		regrasSalvarCliente.add(vGenero);
		
		/* ------ ALTERAR -------- */
		regrasAlterarCliente.add(vNomeAlterado);
		//regrasAlterarCliente.add(vCPFAlterado);
		regrasAlterarCliente.add(vEmail);
		//regrasAlterarCliente.add(vSenha);
		regrasAlterarCliente.add(vSenhaConfSenhaIgual);
		regrasAlterarCliente.add(vDtNascimentoAlterado);
		regrasAlterarCliente.add(vTelefone);
		regrasAlterarCliente.add(vGeneroAlterado);
		
		/* ---------------------------------------------------------------------------------------------------------------- */

		
		/* ----- Adicionando as Strategy's na lista do ENDERE�O ----- */
		
		/* ----- SALVAR ----- */
		//regrasSalvarEndereco.add(vcep);
		regrasSalvarEndereco.add(vLogradouro);
		regrasSalvarEndereco.add(vNumero);
		regrasSalvarEndereco.add(vBairro);
		regrasSalvarEndereco.add(vCidade);
		regrasSalvarEndereco.add(vEstado);
		regrasSalvarEndereco.add(vPais);
		regrasSalvarEndereco.add(vTipoResidencia);
		
		/* ----- ALTERAR ----- */
		regrasAlterarEndereco.add(vCEPAlterado);
		regrasAlterarEndereco.add(vLogradouroAlterado);
		regrasAlterarEndereco.add(vNumAlterado);
		regrasAlterarEndereco.add(vBairroAlterado);
		regrasAlterarEndereco.add(vCidadeAlterado);
		regrasAlterarEndereco.add(vEstadoAlterado);
		regrasAlterarEndereco.add(vPaisAlterado);
		regrasAlterarEndereco.add(vTipoResidenciaAlterado);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* --- Adicionando as Strategy's do CART�O DE CR�DITO ------- */
		
		/* ----- SALVAR ----- */
		regrasSalvarCartaoCredito.add(vNumCartao);
		regrasSalvarCartaoCredito.add(vBandeiraCartao);
		
//		regrasSalvarCartaoCredito.add(vCVV);
//		regrasSalvarCartaoCredito.add(vNomeCartao);
		
//		/* ----- ALTERAR ----- */
//		regrasAlterarCartaoCredito.add(vNumCartaoAlterado);
//		regrasAlterarCartaoCredito.add(vCVVAlterado);
//		regrasAlterarCartaoCredito.add(vNomeCartaoAlterado);
		
		/* ------------------------------------------------------------------------------------------------------------ */
		
		/* ----- Adicionando as Strategy's na lista do LOGIN ----- */
		/* ----- SALVAR ----- */
		regrasSalvarLogin.add(vExisteEmailSenha);
		
		/* ----- CONSULTAR ----- */
		regrasConsultarLogin.add(vExisteEmailSenha);
		regrasConsultarLogin.add(vSenhaLogin); 
		
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do PRODUTO ----- */
//		regrasAlterarProduto.add(vNomeProduto);
//		regrasAlterarProduto.add(vCategoria);
//		regrasAlterarProduto.add(vPrecoCompra);
//		regrasAlterarProduto.add(vPrecoVenda);
//		regrasAlterarProduto.add(vDtCadastro);
//		regrasAlterarProduto.add(vFoto);
//		regrasAlterarProduto.add(vQtde);
//		regrasAlterarProduto.add(vDescricao);
//		regrasAlterarProduto.add(vStatusProduto);
//		regrasAlterarProduto.add(vGrupoPrecificacao);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do CARRINHO ----- */
		//regrasAlterarCarrinho.add(vValorQtdeCarrinho);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do PEDIDO ----- */
		regrasSalvarPedido.add(vTotalPedido);
		regrasSalvarPedido.add(vEnderecoPedido);
		regrasSalvarPedido.add(vFormaDePagamentoPedido);
		regrasSalvarPedido.add(vCartaoPedido);
		 /** regrasSalvarPedido.add(vCupomBonificacaoPedido);
		 * regrasSalvarPedido.add(vStatusPedido); 
		 * regrasSalvarPedido.add(VDataCadastro);
		 */
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do CUPOM ----- */
		regrasConsultarVerificaCupom.add(vCupom);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do ESTOQUE ----- */
		/* ----- SALVAR ----- */
		//regrasSalvarEstoque.add(vEntradaEstoque);
		//regrasSalvarEstoque.add(vSaidaEstoque);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do GR�FICO ----- */
		
		/* ----- CONSULTAR ----- */
		//regrasConsultarGrafico.add(vGrafico);
		
		/* --------------------------------------------------------------------------------------------------------------- */
		
		
		/* ----- REGRAS DA ENTIDADE CLIENTE ----- */
		/* ----- SALVAR ----- */
		regrasCliente.put("SALVAR", regrasSalvarCliente);
		/* ----- CONSULTAR ----- */
		regrasCliente.put("CONSULTAR", regrasConsultarCliente);
		/* ----- ALTERAR ----- */
		regrasCliente.put("ALTERAR", regrasAlterarCliente);
		/* ----- EXCLUIR ----- */
		regrasCliente.put("EXCLUIR", regrasExcluirCliente);
		/* --------------------------------------------------------------------------------------------------------------- */

		/* ----- REGRAS DA ENTIDADE ENDERE�O ----- */
		/* ----- SALVAR ----- */
		regrasEndereco.put("SALVAR", regrasSalvarEndereco);
		/* ----- CONSULTAR ----- */
		regrasEndereco.put("CONSULTAR", regrasConsultarEndereco);
		/* ----- ALTERAR ----- */
		regrasEndereco.put("ALTERAR", regrasAlterarEndereco);
		/* ----- EXCLUIR ----- */
		regrasEndereco.put("EXCLUIR", regrasExcluirEndereco);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CARTAO DE CREDITO ----- */
		/* ----- SALVAR ----- */
		regrasCartaoCredito.put("SALVAR", regrasSalvarCartaoCredito);
		/* ----- CONSULTAR ----- */
		regrasCartaoCredito.put("CONSULTAR", regrasConsultarCartaoCredito);
		/* ----- ALTERAR ----- */
		regrasCartaoCredito.put("ALTERAR", regrasAlterarCartaoCredito);
		/* ----- EXCLUIR ----- */
		regrasCartaoCredito.put("EXCLUIR", regrasExcluirCartaoCredito);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE LOGIN ----- */
		/* ----- SALVAR ----- */
		regrasLogin.put("SALVAR", regrasSalvarLogin);
		/* ----- CONSULTAR ----- */
		regrasLogin.put("CONSULTAR", regrasConsultarLogin);
		/* ----- ALTERAR ----- */
		regrasLogin.put("ALTERAR", regrasAlterarLogin);
		/* ----- EXCLUIR ----- */
		regrasLogin.put("EXCLUIR", regrasExcluirLogin);
		/* --------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PRODUTO ----- */
		/* ----- SALVAR ----- */
		regrasProduto.put("SALVAR", regrasSalvarProduto);
		/* ----- CONSULTAR ----- */
		regrasProduto.put("CONSULTAR", regrasConsultarProduto);
		/* ----- ALTERAR ----- */
		regrasProduto.put("ALTERAR", regrasAlterarProduto);
		/* ----- EXCLUIR ----- */
		regrasProduto.put("EXCLUIR", regrasExcluirProduto);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE DETALHE DO PRODUTO ----- */
		/* ----- SALVAR ----- */
		regrasDetalheProduto.put("SALVAR", regrasSalvarDetalheProduto);
		
		/* -------------------------------------------------------------------------------------------------------------- */
		/* ----- REGRAS DA ENTIDADE CARRINHO ----- */
		/* ----- SALVAR ----- */
		regrasCarrinho.put("SALVAR", regrasSalvarCarrinho);
		/* ----- CONSULTAR ----- */
		regrasCarrinho.put("CONSULTAR", regrasConsultarCarrinho);
		/* ----- ALTERAR ----- */
		regrasCarrinho.put("ALTERAR", regrasAlterarCarrinho);
		/* ----- EXCLUIR ----- */
		regrasCarrinho.put("EXCLUIR", regrasExcluirCarrinho);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO ----- */
		/* ----- SALVAR ----- */
		regrasPedido.put("SALVAR", regrasSalvarPedido);
		/* ----- CONSULTAR ----- */
		regrasPedido.put("CONSULTAR", regrasConsultarPedido);
		/* ----- ALTERAR ----- */
		regrasPedido.put("ALTERAR", regrasAlterarPedido);
		/* ----- EXCLUIR ----- */
		regrasPedido.put("EXCLUIR", regrasExcluirPedido);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE VERIFICA CUPOM ----- */
		/* ----- SALVAR ----- */
		/* ----- CONSULTAR ----- */
		regrasVerificaCupom.put("CONSULTAR", regrasConsultarVerificaCupom);
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CUPOM CARRINHO ----- */
		/* ----- SALVAR ----- */
		regrasCupomCarrinho.put("SALVAR", regrasSalvarCupomCarrinho);
		/* ----- CONSULTAR ----- */
		regrasCupomCarrinho.put("CONSULTAR", regrasConsultarCupomCarrinho);
		/* ----- ALTERAR ----- */
		regrasCupomCarrinho.put("ALTERAR", regrasAlterarCupomCarrinho);
		/* ----- EXCLUIR ----- */
		regrasCupomCarrinho.put("EXCLUIR", regrasExcluirCupomCarrinho);
		/* -------------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO TROCA ----- */
		/* ----- SALVAR ----- */
		regrasPedidoTroca.put("SALVAR", regrasSalvarPedidoTroca);
		/* ----- CONSULTAR ----- */
		regrasPedidoTroca.put("CONSULTAR", regrasConsultarPedidoTroca);
		/* ----- ALTERAR ----- */
		regrasPedidoTroca.put("ALTERAR", regrasAlterarPedidoTroca);
		/* ----- EXCLUIR ----- */
		regrasPedidoTroca.put("EXCLUIR", regrasExcluirPedidoTroca);
		/* ------------------------------------------------------------------------------------------------------------- */
		
		
		/* ----- REGRAS DA ENTIDADE ESTOQUE ----- */
		/* ----- SALVAR ----- */
		regrasEstoque.put("SALVAR", regrasSalvarEstoque);
		/* ----- CONSULTAR ----- */
		regrasEstoque.put("CONSULTAR", regrasConsultarEstoque);
		/* ----- ALTERAR ----- */
		regrasEstoque.put("ALTERAR", regrasAlterarEstoque);
		/* ----- EXCLUIR ----- */
		regrasEstoque.put("EXCLUIR", regrasExcluirEstoque);
		/* ----------------------------------------------------------------------------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE GRAFICO ----- */
		/* ----- SALVAR ----- */
		regrasGrafico.put("SALVAR", regrasSalvarGrafico);
		/* ----- CONSULTAR ----- */
		regrasGrafico.put("CONSULTAR", regrasConsultarGrafico);
		/* ----- ALTERAR ----- */
		regrasGrafico.put("ALTERAR", regrasAlterarGrafico);
		/* ----- EXCLUIR ----- */
		regrasGrafico.put("EXCLUIR", regrasExcluirGrafico);
		/* ------------------------------------------------------------------------------------------------------------ */
		
		
		/* -------------- REGRAS GERAIS ----------- */
		regrasGeral.put(Cliente.class.getName(), regrasCliente);
		regrasGeral.put(Endereco.class.getName(), regrasEndereco);
		regrasGeral.put(CartaoCredito.class.getName(), regrasCartaoCredito);
		regrasGeral.put(Usuario.class.getName(), regrasLogin);
		regrasGeral.put(Produto.class.getName(), regrasProduto);
		regrasGeral.put(DetalheProduto.class.getName(), regrasDetalheProduto);
		regrasGeral.put(Carrinho.class.getName(), regrasCarrinho);
		regrasGeral.put(Pedido.class.getName(), regrasPedido);
		regrasGeral.put(VerificaCupom.class.getName(), regrasVerificaCupom);
		regrasGeral.put(PedidoTroca.class.getName(), regrasPedidoTroca);
		
		//regrasGeral.put(Cupom.class.getName(), regrasCupom);
		//regrasGeral.put(CupomCarrinho.class.getName(), regrasCupomCarrinho);
		//regrasGeral.put(Estoque.class.getName(), regrasEstoque);
		//regrasGeral.put(GraficoAnalise.class.getName(), regrasGrafico);
		/* ------------------------------------------------------------------------------------------------------------ */
	}

	/*------- SALVAR -------*/
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		// retornar o nome do pacote com o nome da classe desta entidade de dominio
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null || msg == "") {
			// Obt�m o DAO correspondente ao nome do pacote com o nome da classe,
			// que esta dentro do HashMap do "daos"
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);

				// cria uma lista para mostrar os salvos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Salvar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- ALTERAR -----------*/
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);

				// cria uma lista para mostrar os alterados
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Alterar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- EXCLUIR --------*/
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);

				// cria uma lista para mostrar os excluidos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("N�o foi poss�vel Excluir o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*--------- CONSULTAR ---------*/
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Nao foi possivel Consultar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	// M�todo para executar as regras de negocio / Strategy
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String msg = "";

		// verifica o nome da classe para pegar o MAP de "regrasGeral"
		String nmClasse = entidade.getClass().getName();
		
		// com o nome da classe, ele pega o MAP com as suas respectivas regras de dominio (exemplo: regrasCliente),
		Map<String, List<IStrategy>> regrasDaEntidade = regrasGeral.get(nmClasse);
		
		// depois ele pega a "opera��o"que deseja ser realizada (exemplo: "salvar")
		List<IStrategy> regrasDaOperacao = regrasDaEntidade.get(operacao);
		
		// para cada "regra" em "regrasDaOperacao", ele ir� chamar as Strategy's 
		// que estiver dentro da lista (exemplo: regrasSalvarCliente)
		for (IStrategy regra : regrasDaOperacao) {
			String resultado = regra.validar(entidade);
			if (resultado != null) {
				msg += " " + resultado + "<br>";
			}
		}

		return msg;
	}
}
