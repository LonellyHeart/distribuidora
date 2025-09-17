/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MATRIZ
 */
public interface VendaProdutoRepository extends JpaRepository<VendaProdutoEntity, Integer> {

    /*
    
    ESSE REPOSITORY NÃO SERA UTILIZADO POR QUE 1 VENDA É 1 IGUAL PRODUTO
    NÃO VOU EXCLUIR ELE, PARA CASO EU QUERO IMPLEMENTAR A FUNÇÃO DE TER UMA VENDA COM MAIS PRODUTOS ELE JA ESTA PRONTO
    
     */
}
