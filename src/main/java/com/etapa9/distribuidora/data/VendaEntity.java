/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "Venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String cpf;

    @NotBlank(message = "Selecione um nome")
    private String nome;

    @NotBlank
    private String contato;

    @NotBlank
    private String endereco;

    @NotBlank
    private String email;

    @NotBlank(message = "Selecione o Produto")
    private String item;

    @NotNull(message = "Insira a quantidade")
    private Integer quantidade;

    @NotNull(message = "Insira o valor unitário")
    private Double unitario;

    @NotNull
    private Double desconto;

    @NotNull
    private Double total;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity clienteEntity;
}

