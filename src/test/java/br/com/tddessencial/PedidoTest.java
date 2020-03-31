package br.com.tddessencial;

import static org.junit.Assert.assertEquals;

import br.com.tddessencial.exception.QuantidadeDeItensInvalidosException;
import br.com.tddessencial.model.ItemPedido;
import br.com.tddessencial.model.Pedido;
import br.com.tddessencial.model.ResumePedido;
import br.com.tddessencial.service.*;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

    private PedidoBuilder pedido;

    @Before
    public void setup(){
      pedido = new PedidoBuilder();
    }

    private void assertResumoPedido(double valorTotal, double desconto){
        ResumePedido resumePedido = pedido.construir().resumo();
        assertEquals(valorTotal, resumePedido.getValorTotal(), 0.0001);
        assertEquals(desconto, resumePedido.getDesconto(), 0.0001);
    }

    @Test
    public  void  devePermitirAdicionarUmItemNoPedido() throws  Exception{
        pedido.comItem(5.80, 5);
    }

    @Test
    public void deveCalcularValorTotalParaPedidoVazio() throws  Exception{
        ResumePedido resumePedido = pedido.construir().resumo();
        assertEquals(0.0, resumePedido.getValorTotal(), 0.0001);
    }

    @Test
    public void deveCalcularValorTotalEDescontoParaPedidoVazio() throws  Exception{
        assertResumoPedido(0.0, 0.0);
    }

    @Test
    public void deveCalcularResumoParaUmItemSemDesconto() throws Exception {
        pedido.comItem( 5.0, 5);
        assertResumoPedido(25.0, 0.0);
    }

    @Test
    public void deveCalcularResumoParaDoisItensSemDesconto() throws  Exception{
        pedido.comItem(3.0, 3)
                .comItem(7.0, 3);

        assertResumoPedido(30.0, 0.0);
    }

    @Test
    public void deveAplicarDescontoNa1aFaixa() throws Exception{
        pedido.comItem(20.0, 20);

        assertResumoPedido(400.0, 16.0);
    }

    @Test
    public void deveAplicarDescontoNa2aFaixa() throws  Exception{

        pedido.comItem(15.0, 30)
                .comItem(15.0, 30);

        assertResumoPedido(900.0, 54.0);
    }

    @Test
    public void deveAplicarDescontoNa3aFaixa() throws  Exception{

        pedido.comItem(15.0, 30)
                .comItem(15.0, 30)
                .comItem(10.0, 30);

        assertResumoPedido(1200, 96.0);
    }

    @Test(expected = QuantidadeDeItensInvalidosException.class )
    public void naoAceitarPedidosComItensComQuantidadeNegativa() throws  Exception{
        pedido.comItem(0.0, -10);
    }

}
