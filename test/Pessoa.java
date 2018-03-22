/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willt
 */
public class Pessoa {
    private String nome;
    private String salario;
    private String profissao;

    public Pessoa() {
    }

    public Pessoa(String nome, 
    String salario, String profissao) {
        this.nome = nome;
        this.salario = salario;
        this.profissao = profissao;
    }
    
    //Getter e Seter... de nome e salario
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    
    

}
