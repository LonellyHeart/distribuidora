package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.FornecedorEntity;
import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.data.ProdutoEntity;
import com.etapa9.distribuidora.service.FornecedorService;
import com.etapa9.distribuidora.service.ProdutoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    // Página de cadastro de produto
    @GetMapping("/cadastro-produto")
    public String mostrarCadastroProduto(HttpSession session, Model model) {
        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");

        if (funcionarioLogado != null) {
            model.addAttribute("funcionario", funcionarioLogado); // Para exibir botão Voltar no sidebar
        } else {
            return "redirect:/login";
        }

        model.addAttribute("produto", new ProdutoEntity());

        // Carrega todos os fornecedores para o select no cadastro_fornecedor.html
        List<FornecedorEntity> fornecedores = fornecedorService.listarFornecedores();
        model.addAttribute("fornecedores", fornecedores);

        return "cadastro_produto";
    }

    // Salvar produto
    @PostMapping("/salvarProduto")
    public String salvarProduto(@Valid @ModelAttribute("produto") ProdutoEntity produto, BindingResult result, Model model, HttpSession session) {

        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");

        if (funcionarioLogado == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("funcionario", funcionarioLogado);
        }

        if (result.hasErrors()) {
            
            List<FornecedorEntity> fornecedores = fornecedorService.listarFornecedores();
            model.addAttribute("fornecedores", fornecedores);

            return "cadastro_produto";
        }

        produtoService.salvarProduto(produto);
        return "redirect:/pagina-inicial";
    }
}
