/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.service;

import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.data.FuncionarioRepository;
import com.etapa9.distribuidora.exception.ResourceNotFoundException;
import com.etapa9.distribuidora.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity criarFuncionario(FuncionarioEntity funcionario) {
        funcionario.setSenha(Criptografia.hashPassword(funcionario.getSenha())); // Essa linha serve para criptografar a senha usando o metodo estatico Criptografia
        return funcionarioRepository.save(funcionario);
    }

    public FuncionarioEntity atualizarFuncionario(Integer id, FuncionarioEntity funcionarioAtualizado) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id " + id));

        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setLogin(funcionarioAtualizado.getLogin());
        funcionario.setSenha(funcionarioAtualizado.getSenha());

        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioEntity> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioEntity getFuncionarioById(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id " + id));
    }

    public void deletarFuncionario(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioEntity autenticar(String login, String senha) {
        FuncionarioEntity funcionario = funcionarioRepository.findByLogin(login);
        if (funcionario != null && funcionario.getSenha().equals(Criptografia.hashPassword(senha))) { // Aqui ele precisa fazer a condição usando a senha criptografada
            return funcionario;
        }
        return null;
    }
}
