/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.dtos;

/**
 * @author Matheus de Carvalho Sobrinho
 */
public class UsuarioLdap {
    private String matricula;
    private String nome;
    
    public UsuarioLdap() {
        
    }

    public UsuarioLdap(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
