/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Ingressos {
    String Nome;
    Integer Idade;
    Double preco;
    String texto;

    public Ingressos() {
    }

 public Ingressos(String Nome, Integer Idade, Double preco, String texto) {
  this.Nome = Nome;
  this.Idade = Idade;
  this.preco = preco;
  this.texto = texto;
 }

 public String getTexto() {
  return texto;
 }

 public void setTexto(String texto) {
  this.texto = texto;
 }

   

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Integer getIdade() {
        return Idade;
    }

    public void setIdade(Integer Idade) {
        this.Idade = Idade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return  "Cliente= " + Nome + ", Idade=" + Idade + ", Total=" + preco + "\nComprou "+texto;
    }
    
}
