package com.les.roupa.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.les.roupa.core.dominio.EntidadeDominio;;

/**
 * Interface IDAO - Principais opera��es - Criar/Salvar, 
 * 					Editar/Alterar, Apagar/Excluir e Visualizar/Consultar
 * @author Lorena Oliveira
 * Fev/2022
 * 
 */

public interface IDAO {
	
	public void salvar(EntidadeDominio entidade) throws SQLException;

    public void alterar(EntidadeDominio entidade) throws SQLException;

    public void excluir(EntidadeDominio entidade) throws SQLException;

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;
}
