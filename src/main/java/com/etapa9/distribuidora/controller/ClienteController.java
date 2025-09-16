/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.ClienteEntity;
import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Página de cadastro
    @GetMapping("/cadastro-cliente")
    public String mostrarCadastroCliente(HttpSession session, Model model) {
        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");

        if (funcionarioLogado != null) {
            model.addAttribute("funcionario", funcionarioLogado);
        }
        else{
            return "redirect:/login";
        }
        
        model.addAttribute("cliente", new ClienteEntity());
        model.addAttribute("mostrarVoltar", true);
        return "cadastro_cliente";
    }

    // Salvar cliente após cadastro
    @PostMapping("/cadastro-cliente")
    public String salvarCliente(@Valid @ModelAttribute("cliente") ClienteEntity cliente, BindingResult result,  Model model, HttpSession session) {
        if (result.hasErrors()) {

            FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");
            if (funcionarioLogado != null) {
                model.addAttribute("funcionario", funcionarioLogado);
            }
            else{
                 return "redirect:/login";
            }
            return "cadastro_cliente";
        }

        clienteService.salvarCliente(cliente);
        return "redirect:/pagina-inicial";
    }
}

