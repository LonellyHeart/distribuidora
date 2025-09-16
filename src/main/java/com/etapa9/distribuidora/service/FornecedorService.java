/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.service;

import com.etapa9.distribuidora.data.FornecedorEntity;
import com.etapa9.distribuidora.data.FornecedorRepository;
import com.etapa9.distribuidora.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Salvar fornecedor
    public FornecedorEntity salvarFornecedor(FornecedorEntity fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    // Atualizar fornecedor
    public FornecedorEntity atualizarFornecedor(Integer id, FornecedorEntity fornecedorAtualizado) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id " + id));

        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setAdicionais(fornecedorAtualizado.getAdicionais());

        return fornecedorRepository.save(fornecedor);
    }

    // Listar todos os fornecedores
    public List<FornecedorEntity> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    // Buscar fornecedor por id
    public FornecedorEntity getFornecedorById(Integer id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id " + id));
    }

    // Deletar fornecedor
    public void deletarFornecedor(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}
