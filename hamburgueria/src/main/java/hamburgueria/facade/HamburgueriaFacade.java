package hamburgueria.facade;

import hamburgueria.caixa.Caixa;
import hamburgueria.combo.*;
import hamburgueria.desconto.DescontoStrategy;
import hamburgueria.fidelidade.FidelidadeStrategy;
import hamburgueria.pagamento.FormaPagamento;
import hamburgueria.pedido.*;
import hamburgueria.produto.*;
import hamburgueria.produto.decorator.AdicionalBacon;
import hamburgueria.produto.decorator.AdicionalQueijo;

/**
 * Façade: unifica a interface de múltiplos subsistemas (combo factory, decorators, 
 * order builder, caixa registradora e formas de pagamento) em uma única classe simplificada.
 */
public class HamburgueriaFacade {

    private final Caixa caixa = Caixa.getInstancia();

    /**
     * Realiza um pedido completo coordenando os diversos subsistemas da hamburgueria.
     *
     * @return valor final pago pelo cliente e registrado no caixa
     */
    public double realizarPedidoCompleto(
            String tipoCombo,
            String tipoPedido,
            FormaPagamento formaPagamento,
            DescontoStrategy desconto,
            FidelidadeStrategy fidelidade,
            boolean comQueijoExtra,
            boolean comBaconExtra) {

        // 1. Instancia o Combo via Abstract Factory
        ComboFactory factory;
        if (tipoCombo.equalsIgnoreCase("Vegano")) {
            factory = new ComboVeganoFactory();
        } else {
            factory = new ComboTradicionalFactory();
        }

        Lanche lanche = factory.criarLanche();
        Bebida bebida = factory.criarBebida();

        // 2. Aplica Adicionais (Decorator)
        if (comQueijoExtra) {
            lanche = new AdicionalQueijo(lanche);
        }
        if (comBaconExtra) {
            lanche = new AdicionalBacon(lanche);
        }

        // 3. Obtém o preço bruto dos itens
        double valorItens = lanche.getPreco() + bebida.getPreco();

        // 4. Monta o pedido via Builder
        PedidoBuilder builder = new PedidoBuilder()
                .comFormaPagamento(formaPagamento)
                .comDesconto(desconto)
                .comFidelidade(fidelidade);

        if (tipoPedido.equalsIgnoreCase("Delivery")) {
            builder.paraDelivery();
        } else {
            builder.paraSalao();
        }

        Pedido pedido = builder.build();

        // 5. Executa o pipeline de fechamento de pedido (que registra no Caixa)
        return pedido.finalizarPedido(valorItens);
    }
}
