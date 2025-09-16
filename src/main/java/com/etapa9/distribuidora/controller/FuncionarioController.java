/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.service.FuncionarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // A Pagina inicial vai ser a tela de cadastro (seguindo o Figma)
    @GetMapping("/")
    public String mostrarCadastroForm(Model model) {
        model.addAttribute("funcionario", new FuncionarioEntity());
        return "cadastro";
    }

    @PostMapping("/salvarFuncionario")
    public String salvarFuncionario(@Valid @ModelAttribute("funcionario") FuncionarioEntity funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        }
        funcionarioService.criarFuncionario(funcionario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam("login") String login, @RequestParam("senha") String senha, HttpSession session, Model model) {

        FuncionarioEntity funcionario = funcionarioService.autenticar(login, senha);

        if (funcionario != null) {
            session.setAttribute("funcionarioLogado", funcionario); //  Esse processo é utilizado para salvar o nome do funcionario logado, para que aparece no navbar.

            return "redirect:/pagina-inicial";
        } else {
            model.addAttribute("erro", "Login ou senha incorretos");
            return "login";
        }
    }   
}
