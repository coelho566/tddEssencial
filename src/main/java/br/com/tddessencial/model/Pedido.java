package br.com.tddessencial.model;

import br.com.tddessencial.exception.QuantidadeDeItensInvalidosException;
import br.com.tddessencial.service.CalculadoraFaixaDesconto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private CalculadoraFaixaDesconto calculadoraFaixaDesconto;

    public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
        this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
    }

    private List<ItemPedido> itens = new ArrayList<>();

    public void adicionarItem(ItemPedido itemPedido) throws Exception {
        if(itemPedido.getQtd() < 0) {
            throw new QuantidadeDeItensInvalidosException();
        }
        itens.add(itemPedido);
    }

    public ResumePedido resumo(){
        double valorTotal = itens.stream().mapToDouble(i -> i.getValor() * i.getQtd()).sum();
        double desconto = calculadoraFaixaDesconto.desconto(valorTotal);

        return new ResumePedido(valorTotal, desconto);
    }

}
