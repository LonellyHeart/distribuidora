/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.FuncionarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaInicialController {

    @GetMapping("/pagina-inicial")
    public String mostrarPaginaInicial(HttpSession session, Model model) {
        
        FuncionarioEntity funcionario = (FuncionarioEntity) session.getAttribute("funcionarioLogado");

        if (funcionario == null) {
            return "redirect:/login";
        }

        model.addAttribute("funcionario", funcionario);
        return "pagina_inicial"; 
    }
}

