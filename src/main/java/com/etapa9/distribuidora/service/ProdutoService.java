/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.service;

import com.etapa9.distribuidora.data.ProdutoEntity;
import com.etapa9.distribuidora.data.ProdutoRepository;
import com.etapa9.distribuidora.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoEntity salvarProduto(ProdutoEntity produto) {

        return produtoRepository.save(produto);
    }

    public ProdutoEntity atualizarProduto(Integer id, ProdutoEntity produtoAtualizado) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id " + id));

        produto.setNome(produtoAtualizado.getNome());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        produto.setFornecedorEntity(produtoAtualizado.getFornecedorEntity());
        produto.setVolumetria(produtoAtualizado.getVolumetria());
        produto.setOrigem(produtoAtualizado.getOrigem());
        produto.setAdicionais(produtoAtualizado.getAdicionais());

        return produtoRepository.save(produto);
    }

    public List<ProdutoEntity> listarProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity getProdutoById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id " + id));
    }

    public void deletarProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
}
