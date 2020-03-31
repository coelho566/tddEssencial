package br.com.tddessencial;

import br.com.tddessencial.model.ItemPedido;
import br.com.tddessencial.model.Pedido;
import br.com.tddessencial.service.*;

public class PedidoBuilder {

    private Pedido instancia;

    public  PedidoBuilder(){
        CalculadoraFaixaDesconto calculadoraFaixaDesconto =
                new CalculadoraDescontoTerceiraFaixa(
                        new CalculaDescontoSegundaFaixa(
                                new CalculaDescontoPrimeiraFaixa(
                                        new SemDesconto(null))));
        instancia = new Pedido(calculadoraFaixaDesconto);
    }

    public PedidoBuilder comItem(double valorUnitario, int quantidade) throws Exception {
        instancia.adicionarItem(new ItemPedido("Gerado", valorUnitario, quantidade));
        return this;
    }

    public Pedido construir(){
        return instancia;
    }
}
