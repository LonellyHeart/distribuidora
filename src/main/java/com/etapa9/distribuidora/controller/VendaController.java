package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.ClienteEntity;
import com.etapa9.distribuidora.data.FuncionarioEntity;
import com.etapa9.distribuidora.data.ProdutoEntity;
import com.etapa9.distribuidora.data.VendaEntity;
import com.etapa9.distribuidora.service.ClienteService;
import com.etapa9.distribuidora.service.ProdutoService;
import com.etapa9.distribuidora.service.VendaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/nova-venda")
    public String novaVendaForm(HttpSession session, Model model) {
        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");
        if (funcionarioLogado == null) {
            return "redirect:/login";
        }
        model.addAttribute("funcionario", funcionarioLogado);

        List<ClienteEntity> clientes = clienteService.listarClientes();
        List<ProdutoEntity> produtos = produtoService.listarProdutos();

        model.addAttribute("clientes", clientes);
        model.addAttribute("produtos", produtos);
        model.addAttribute("vendaEntity", new VendaEntity());
        
        model.addAttribute("mostrarValor", true);  // Para exibir botão Voltar no sidebar
        return "nova_venda"; // Nome do template HTML
    }

    @PostMapping("/salvarVenda")
    public String salvarVenda(@Valid @ModelAttribute("vendaEntity") VendaEntity venda, BindingResult result, HttpSession session, Model model) {
        
        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");
        if (funcionarioLogado == null) {
            return "redirect:/login";
        }
        model.addAttribute("funcionario", funcionarioLogado);

        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.listarClientes());
            model.addAttribute("produtos", produtoService.listarProdutos());
            return "nova_venda";
        }

        // Salva a venda
        vendaService.salvarVenda(venda);

        return "redirect:/nova-venda";
    }
}
