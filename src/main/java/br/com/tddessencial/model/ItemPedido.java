package br.com.tddessencial.model;

public class ItemPedido {
    private final String nome;
    private final double valor;
    private final int qtd;

    public ItemPedido(String nome, double valor, int qtd) {
        this.nome = nome;
        this.valor = valor;
        this.qtd = qtd;
    }


    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQtd() {
        return qtd;
    }
}
