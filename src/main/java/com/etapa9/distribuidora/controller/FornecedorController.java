/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.FornecedorEntity;
import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.service.FornecedorService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    // Página de cadastro
    @GetMapping("/cadastro-fornecedor")
    public String mostrarCadastroFornecedor(HttpSession session, Model model) {
        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");

        if (funcionarioLogado != null) {
            model.addAttribute("funcionario", funcionarioLogado);
        } else {
            return "redirect:/login";
        }

        model.addAttribute("fornecedor", new FornecedorEntity());
        model.addAttribute("mostrarVoltar", true); // Para exibir botão Voltar no sidebar
        return "cadastro_fornecedor";
    }

    // Salvar fornecedor
    @PostMapping("/salvarFornecedor")
    public String salvarFornecedor(@Valid @ModelAttribute("fornecedor") FornecedorEntity fornecedor, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");
            if (funcionarioLogado != null) {
                model.addAttribute("funcionario", funcionarioLogado);
            } else {
                return "redirect:/login";
            }
            return "cadastro_fornecedor";
        }

        fornecedorService.salvarFornecedor(fornecedor);
        return "redirect:/pagina-inicial";
    }
}

