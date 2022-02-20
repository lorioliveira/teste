package com.les.roupa.core.strategy.impl;

import com.les.roupa.core.strategy.IStrategy;
import com.les.roupa.core.dominio.Cliente;
import com.les.roupa.core.dominio.EntidadeDominio;
import com.les.roupa.core.dominio.Produto;

/**
 * Classe para validar o campo descricao do produto
 * 
 */
public class ValidarDescricao implements IStrategy {

	@Override
	public String validar(EntidadeDominio entidade) {
		Produto produto = (Produto)entidade;
		
		// se o "alteraProduto" for igual a 1, executa essa regra
		if(produto.getAlteraProduto().contentEquals("1")) {
			if(produto.getDescricao() == null || produto.getDescricao().equals("")) {
				return ("- Insira uma descricao para o produto.  <br>");
			}
			else {
				return null;
			}
		}else {
			return null;
		}
	}

}