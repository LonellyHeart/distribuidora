/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.etapa9.distribuidora.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author MATRIZ
 */
public class ProdutoEntity {
    @Data
@Entity
@Table(name = "Produto")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String nome;
    
    private String ornecedor;

    @NotBlank(message = "Volumetria obrigatório")
    private String volumetria;

    @NotBlank(message = "Pais de origem obrigatório")
    private String origem;

    @NotNull(message = "Salário obrigatório")
    private double salario;
}
}
