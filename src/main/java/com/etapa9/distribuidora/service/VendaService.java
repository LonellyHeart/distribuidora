package com.etapa9.distribuidora.service;

import com.etapa9.distribuidora.data.ClienteEntity;
import com.etapa9.distribuidora.data.ProdutoEntity;
import com.etapa9.distribuidora.data.VendaEntity;
import com.etapa9.distribuidora.data.VendaRepository;
import com.etapa9.distribuidora.data.VendaProdutoEntity;
import com.etapa9.distribuidora.data.VendaProdutoRepository;
import com.etapa9.distribuidora.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;

    public List<VendaEntity> listarVendas() {
        return vendaRepository.findAll();
    }

    public VendaEntity salvarVenda(VendaEntity venda) {
        return vendaRepository.save(venda);
    }
    
        public VendaEntity getClienteById(Integer id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado com id " + id));
    }

    public VendaProdutoEntity salvarVendaProduto(VendaProdutoEntity vp) {
        return vendaProdutoRepository.save(vp);
    }
}
