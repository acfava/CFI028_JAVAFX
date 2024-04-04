/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cfi028_prova1_2024_Solucao;

/**
 *
 * @author Aluno
 */
public class Vacinacao {
    String nome;
    String data;
    Double peso;
    String genero;
    String vacinas;
    Double preco;
    Double precoDesconto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getVacinas() {
        return vacinas;
    }

    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPrecoDesconto() {
        return precoDesconto;
    }

    public void setPrecoDesconto(Double precoDesconto) {
        this.precoDesconto = precoDesconto;
    }

    public Vacinacao() {
    }

    public Vacinacao(String nome, String data, Double peso, String genero, String vacinas, Double preco, Double precoDesconto) {
        this.nome = nome;
        this.data = data;
        this.peso = peso;
        this.genero = genero;
        this.vacinas = vacinas;
        this.preco = preco;
        this.precoDesconto = precoDesconto;
    }

    @Override
    public String toString() {
        return "Vacinacao{" + "nome=" + nome + ", data=" + data + ", peso=" + peso + ", genero=" + genero + ", vacinas=" + vacinas + ", preco=" + preco + ", precoDesconto=" + precoDesconto + '}';
    }
    
}
