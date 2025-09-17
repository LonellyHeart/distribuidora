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
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "Funcionario")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Informe ao menos 2 caracteres para o campo nome")
    private String nome;
    
    @Size(min = 4, message = "Informe ao menos 4 caracteres para o campo login")
    private String login;

    @Size(min = 6, message = "Informe ao menos 6 caracteres para o campo senha")
    private String senha;

}
