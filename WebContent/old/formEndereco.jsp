<%@page import='com.les.roupa.core.dao.*'%>
<%@page import='com.les.roupa.core.dominio.*'%>
<%@page import='com.les.roupa.core.dao.impl.*'%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Mirror Fashion</title>
		<link rel="stylesheet" type="text/css" href="../css/enderecos.css">
		<meta charset="utf-8">	
		<!-- CÓDIGO PARA ACEITAR ACENTUACAO DO ARQUIVO .JS-->
		 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>	
	</head>
	<header class="container">
		<h1><a href="../JSP/index.jsp"><img src="../img/logo.png" alt="Mirror Fashion"></a></h1>
		<p class="sacola"><a href="../HTML/carrinho.html">Minha Sacola</a></p>
		<nav class="menu-opcoes" class="links">
			<ul>
				<li><a href="../JSP/index.jsp">Home</a></li>
				<li><a href="../JSP/produtos.jsp">Produtos</a></li>
				<li><a href="../JSP/perfil.jsp">Perfil</a></li>
			</ul>
		</nav>
	</header>
	<body>
		<!-- CONTAINER - DESTAQUE - PARTE PRINCIPAL-->
		<div class="container destaque">
			<!--Seção do MENU PERFIL -->
			<section class="menu-departamentos">
				<h2 style="margin-left: 5px;">Perfil</h2>
				<nav>
					<li>
						<a href="../JSP/meusdados.jsp">Meus Dados</a>
						<ul>
							<li><a href="../JSP/meusenderecos.jsp">Endereços</a></li>
							<li><a href="../JSP/perfil.jsp">Cartões e Cupons</a></li>
							<li><a href="../JSP/alterarsenha.jsp">Alterar Senha</a></li>
						</ul>
					</li>
					<li><a href="">Meus Pedidos</a></li>
					<li onclick="sair()">
						<a href="../JSP/login.jsp">Sair</a>
						<!-- ALERTA QUANDO SAIR DA CONTA -->
						<script>
							function sair(){
								alert("Você saiu com sucesso! Volte em breve!");
							}
						</script>
					</li>
				</nav>
			</section>
			<!-- FIM DO PERFIL-->
			<!-- DADOS  DE ENDEREOO  -->
			<form class="formulario" action="http://localhost:8080/eCommerce_roupa/cadastroEndereco" >

				<div>* ENDEREÇOS *</div><br>
				<div>
					<label>Tipo Residência: </label>
					<select id="tipoResidencia" name="tipoResidencia">
						<option value="casa">Casa</option>
						<option value="apartamento">Apto</option>
						<option value="outro">Outro</option>
					</select>
					<label class="CEP">CEP:</label><input type="text" id="CEP" name="cep" class="form-control cep-mask" placeholder="00000-000"  />
				</div>
				<label id="logradouro">Logradouro:</label> <input type="text" name="logradouro" id="logradouro" />
				<label id="numero">Número:</label> <input type="text" name="numero" id="numero"  />
				
					<label id="bairro">Bairro:</label><input type="text" name="bairro" id="bairro"  />
					<!-- Estado / Cidade / País -->
					<label> Estado: </label>
					<select id="estado" name="estado" onchange="buscaCidades(this.value)">
						<option value="">Selecione Estado</option>
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapa</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceara</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espirito Santo</option>
						<option value="GO">Goias</option>
						<option value="MA">Maranhao</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Para</option>
						<option value="PB">Paraiba</option>
						<option value="PR">Parana</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piaui</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande do Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondonia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">Sao Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantins</option>
					</select>
				
					<label for="Cidade"> Cidade: </label>
					<select id="cidade" name="cidade">
						<option value="">Selecione o Estado</option>
						<script type="text/javascript" src="../js/estados-cidades.js" charset="utf-8"></script>
					</select>
				
					<label>Pais: </label>
					<select id="pais" name="pais">
						<option value="Brasil" selected>Brasil</option>
					</select>
				
				<textarea id="textarea" placeholder="Campo para observações (opcional)" name="observacoes"></textarea>

				<!-- BOTÕES -->
				<div class="button">
					<button type="submit" onclick="window.history.go(-1); return false;">Voltar</button>
					<button type="submit" name="operacao" value="SALVAR"> Salvar </button>
				</div>
			</form>
        </div>
		
		<!-- FIM DOS DADOS DE ENDERECOS  -->
		
		<!-- RODAPE -->
		<footer>
			<div class="container">
				<a href="../JSP/index.jsp">
				<img src="../img/logo-rodape.png" alt="Logo Mirror Fashion"></a>
				<ul class="social">
					<li><a href="http://facebook.com/mirrorfashion">Facebook</a></li>
					<li><a href="http://twitter.com/mirrorfashion">Twitter</a></li>
					<li><a href="http://plus.google.com/mirrorfashion">Google+</a></li>
				</ul>
			</div>
		</footer>
		<!-- FIM DO RODAPE -->
		<!-- Para habilitar estados e cidades  -->
		<script src="../js/estados-cidades.js"> </script>
	</body>
</html>