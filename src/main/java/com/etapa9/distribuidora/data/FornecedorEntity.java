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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Entity
@Table(name = "Funcionario")
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 10, message = "Informe ao menos 2 caracteres para o campo razão social")
    private String nome;

    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotBlank(message = "Endereço obrigatório")
    private String endereco;

    @NotBlank(message = "Telefone obrigatório")
    private String telefone;

    private String adicionais;
}
