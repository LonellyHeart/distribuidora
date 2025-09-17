package com.etapa9.distribuidora.controller;

import com.etapa9.distribuidora.data.ClienteEntity;
import com.etapa9.distribuidora.data.ClienteRepository;
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

    @Autowired
    private ClienteRepository clienteRepository;

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

        model.addAttribute("mostrarVoltar", true); // Para exibir botão Voltar no sidebar
        return "nova_venda"; // Nome do template HTML
    }

    @PostMapping("/salvarVenda")
    public String salvarVenda(@RequestParam("clienteEntity.id") int clienteId, @Valid @ModelAttribute("vendaEntity") VendaEntity venda, BindingResult result, HttpSession session, Model model) {

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

        ClienteEntity cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        venda.setClienteEntity(cliente);// Essa é a chave estrangeira do Cliente na Venda

        // Campos obrigatórios do cliente
        venda.setNome(cliente.getNome());
        venda.setCpf(cliente.getCpf());
        venda.setContato(cliente.getContato());
        venda.setEndereco(cliente.getEndereco());
        venda.setEmail(cliente.getEmail());

        vendaService.salvarVenda(venda);

        return "redirect:/nova-venda";
    }

    @GetMapping("/lista-venda")
    public String listaVendas(HttpSession session, Model model) {

        FuncionarioEntity funcionarioLogado = (FuncionarioEntity) session.getAttribute("funcionarioLogado");
        if (funcionarioLogado == null) {
            return "redirect:/login";
        }
        model.addAttribute("funcionario", funcionarioLogado);
        
        List<VendaEntity> vendas = vendaService.listarVendas();
        model.addAttribute("vendas", vendas);

        model.addAttribute("mostrarVoltar", true); // Para exibir botão Voltar no sidebar
        return "lista_venda";
    }
}
